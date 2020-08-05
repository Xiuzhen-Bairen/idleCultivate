package com.idleCultivate.game.controller;

import com.idleCultivate.character.model.Character;
import com.idleCultivate.character.service.CharacterService;
import com.idleCultivate.common.model.CommonResult;
import com.idleCultivate.game.GameWorld;
import com.idleCultivate.user.model.UserAccount;
import com.idleCultivate.user.service.UserService;
import com.idleCultivate.util.cipher.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/game")
public class GameController {
    private static final Logger logger = LogManager.getLogger(GameController.class);

    @Autowired
    UserService userService;
    @Autowired
    CharacterService characterService;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private HttpServletRequest request;

    @ResponseBody
    @RequestMapping("/register")
    public Object register(String username, String password) {
        String ip = request.getRemoteAddr();
        String regex = "[a-zA-Z0-9]{6,20}";
        if (!Pattern.matches(regex, username)) {
            return CommonResult.fail("用户名应为6-20位英文字母、数字");
        }

        if (!Pattern.matches(regex, password)) {
            return CommonResult.fail("密码应为6-20位英文字母、数字");
        }

        String cipher = MD5Util.md5(password);
        CommonResult commonResult = userService.register(username, cipher, ip);
        return commonResult;
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(String username, String password) {
        String cipher = MD5Util.md5(password);
        CommonResult commonResult = userService.login(username, cipher);
        if (commonResult.isSuccess()) {
            UserAccount userAccount = (UserAccount) commonResult.getData();
            httpSession.setAttribute(GameWorld.SK_User, userAccount);
        }

        return commonResult;
    }

    @RequestMapping("/characters")
    public Object characterList(RedirectAttributes redirectAttributes) {
        try {
            UserAccount userAccount = (UserAccount) httpSession.getAttribute(GameWorld.SK_User);
            String userId = userAccount.getId();
            CommonResult commonResult = characterService.listUserCharacters(userId);
            if (!commonResult.isSuccess()) {
                throw new Exception("获取角色列表失败！" + commonResult.getMessage());
            }

            List<Character> list = (List<Character>) commonResult.getData();
            request.setAttribute("characters", list);
            return "/game/characters";
        } catch (Exception ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
            return "redirect:/game/error";
        }
    }

    @RequestMapping("/createChar")
    public Object createChar() {
        return "/game/createChar";
    }

    @ResponseBody
    @RequestMapping(value = "/createChar", method = RequestMethod.POST)
    public Object createChar(String name, String faction, String race, String job) {
        UserAccount userAccount = (UserAccount) httpSession.getAttribute(GameWorld.SK_User);
        String userId = userAccount.getId();
        if (StringUtils.isBlank(name)) {
            return CommonResult.fail("角色昵称不能为空！");
        }

        if (name.length() < 2 || name.length() > 10) {
            return CommonResult.fail("角色昵称应为 2-10 个字符！");
        }

        String pattern = "^[\\u4e00-\\u9fa5_a-zA-Z0-9]+$";
        if (!Pattern.matches(pattern, name)) {
            return CommonResult.fail("角色名称只能包含汉字、英文字母、数字、下划线！");
        }

        if (StringUtils.isBlank(faction)) {
            return CommonResult.fail("请选择阵营！");
        }

        if (StringUtils.isBlank(race)) {
            return CommonResult.fail("请选择种族！");
        }

        if (StringUtils.isBlank(job)) {
            return CommonResult.fail("请选择职业！");
        }


        CommonResult commonResult = characterService.createCharacter(userId, name, faction, race, job);
        return commonResult;
    }

    @RequestMapping("/main")
    public Object main(String characterId, RedirectAttributes redirectAttributes) {
        if (characterId == null) {
            if (httpSession.getAttribute(GameWorld.SK_CharId) != null) {
                characterId = httpSession.getAttribute(GameWorld.SK_CharId).toString();
            } else {
                request.setAttribute("error", "登陆超时，请重新登陆！");
                return "redirect:/game/error";
            }
        }

        CommonResult commonResult = characterService.find(characterId);
        if (commonResult.isSuccess()) {
            httpSession.setAttribute(GameWorld.SK_CharId, characterId);
            return "/game/main";
        } else {
            logger.warn("读取角色信息失败！charId: " + characterId + " message: " + commonResult.getMessage());
            redirectAttributes.addFlashAttribute("error", "读取角色信息失败！");
            return "redirect:/game/error";
        }
    }

    @RequestMapping("/error")
    public Object error() {
        return "/game/error";
    }
}
