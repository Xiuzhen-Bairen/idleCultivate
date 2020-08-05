const SocketHub = function (url) {
    this.webSocket = new WebSocket(url);
    this.webSocket.onopen = function (event) {
        console.log('WebSocket建立连接');
        wowClient.onConnected(event);
    };
    this.webSocket.onmessage = function (event) {
        // console.log('WebSocket收到消息：%c' + event.data, 'color:green');
        let message = JSON.parse(event.data) || {};
        wowClient.onReceive(message);
    };
    this.webSocket.onclose = function (event) {
        console.log('WebSocket关闭连接');
    };
    this.webSocket.onerror = function (event) {
        console.log('WebSocket发生异常');
    };
    // 发送消息
    this.send = function (message) {
        let msg = JSON.stringify(message);
        this.webSocket.send(msg);
    };
    // 关闭
    this.close = function () {
        this.webSocket.close();
    };
};

const WowClient = function() {
    this.cache = {
        version: 0,
        levelExpMap: []
    };
    this.character = {
        id: "",
        name: "",
        race: "",
        job: "",
        faction: "",
        level: "",
        gold: "",
        exp: "",
        maxExp: ""
    };

    this.moveTime = 0;
    this.isBattle = 0;
    this.battleMobId = '';
    this.battleMobTime = 0;
    this.battleInterval = {};
    this.hubUrl = "ws://localhost:20010/hub";
    this.clientVersion = "1.0";
    this.cacheKey = "idleCultivate_client_cache";
    this.socketHub = new SocketHub(this.hubUrl);
};

const RequestMessage = function () {
    this.header = {
        messageCode: "",
        requestTime: new Date(),
        version: wowClient.clientVersion
    };
    this.content = {};
};

const MessageCode = {
    // 客户端发送的消息类型
    CLoadCache: "30000001",     // 缓存加载
    CLogin: "30001001",         // 登陆
    CLoadMap: "30001002",       // 读取地图信息
    CLoadOnline: "30001003",    // 读取在线列表
    CLoadChatacter: "30001004", // 加载角色信息
    CChat: "30002001",          // 聊天
    CMove: "30002002",          // 地图移动
    CBattleMob: "30003001",     // 在线打怪
    // 服务端发送的消息类型
    SLoadCache: "60000001",     // 缓存加载
    SLoadMap: "60001002",       // 读取地图信息
    SLoadOnline: "60001003",    // 读取在线列表
    SLoadCharacter: "60001004", // 加载角色信息
    SChat: "60002001",          // 聊天
    SBattleMob: "60003001"      // 在线打怪
};

const ChatChannel = {
    Local: "1",
    World: "2",
    Faction: "3",
    Team: "4",
    Whisper: "5"
};

const TimeLag = {
    Move: 60,
    BattleMob: 30
};

RequestMessage.prototype = {
    loadCache: function () {
        this.header.messageCode = MessageCode.CLoadCache;
    },
    login: function () {
        this.header.messageCode = MessageCode.CLogin;
    },
    loadMap: function() {
        this.header.messageCode = MessageCode.CLoadMap;
    },
    loadOnline: function() {
        this.header.messageCode = MessageCode.CLoadOnline;
    },
    loadCharacter: function(){
        this.header.messageCode = MessageCode.CLoadChatacter;
    },
    chat: function (param) {
        this.header.messageCode = MessageCode.CChat;
        this.content = param;
    },
    move: function (mapId) {
        this.header.messageCode = MessageCode.CMove;
        this.content = {
            destMapId: mapId
        };
    },
    battleMob: function (mobId) {
        this.header.messageCode = MessageCode.CBattleMob;
        this.content = {
            mobId: mobId
        };
    }
};

WowClient.prototype = {
    // 连接建立事件
    onConnected: function() {
        let that = this;
        // 加载缓存
        let storage = localStorage.getItem(this.cacheKey);
        let cache = storage ? JSON.parse(storage) : null;
        if (!cache || (new Date().getTime() - cache.version) > 1000 * 60 * 60 * 24) {
            that.sendLoadCache();
        } else {
            that.cache = cache;
        }
        // 发送登陆消息
        that.sendLogin();
    },
    // 接收消息事件
    onReceive: function(message) {
        console.log("onReceive");
        console.log(message);
        if (message.header) {
            if (message.header.version !== this.clientVersion) {
                console.log('undefined message.header');
                alert('版本已更新，请关闭后重新登陆！');
                return;
            }

            if (message.content) {
                let content = message.content;
                switch (message.header.messageCode) {
                    case MessageCode.SLoadCache:
                        this.loadCache(content);
                        break;
                    case MessageCode.SLoadCharacter:
                        this.loadCharacterInfo(content);
                        break;
                    case MessageCode.SLoadMap:
                        this.loadMapInfo(content);
                        break;
                    case MessageCode.SLoadOnline:
                        this.loadOnlineInfo(content);
                        break;
                    case MessageCode.SChat:
                        this.loadChat(content);
                        break;
                    case MessageCode.SBattleMob:
                        this.loadBattleMobResult(content);
                        break;
                    default:
                        break;
                }
            } else {
                console.log('message.content is null');
            }
        }
    },

    //////////////////
    //// 接收消息 ////
    //////////////////

    // 加载缓存
    loadCache: function (data) {
        this.cache.levelExpMap = data.levelExpMap;
        this.cache.version = new Date().getTime();
        localStorage.setItem(this.cacheKey, JSON.stringify(this.cache));
    },
    // 加载角色信息
    loadCharacterInfo: function (data) {
        let vo = data.character;
        this.character.id = vo.id;
        this.character.name = vo.name;
        this.character.level = vo.level;
        this.character.job = vo.job;
        this.character.race = vo.race;
        this.character.faction = vo.faction;
        this.character.gold = vo.gold;
        this.character.exp = vo.experience;
        this.character.maxExp = this.cache.levelExpMap[vo.level];

        $('#char-name').html(vo.name);
        $('#char-level').html(vo.level);
        $('#char-faction').html(enumUtil.faction(vo.faction));
        $('#char-race').html(enumUtil.race(vo.race));
        $('#char-job').html(enumUtil.job(vo.job));
        $('#char-exp').html(vo.experience + " / " + this.character.maxExp);
    },
    // 加载地图信息
    loadMapInfo: function (data) {
        let map = data.mapInfo.map;
        let mapCoords = data.mapInfo.mapCoords;
        $('#mapName').html(map.name);
        $('#mapDesc').html(map.description);
        $('#mapImg').attr('src', '/images/wow/map/' + map.name + '.jpg');
        let coordsHtml = '';
        for (let index in mapCoords) {
            let mapCoord = mapCoords[index];
            coordsHtml += '<area shape="' + mapCoord.shape + '" coords="' + mapCoord.coord + '" onclick="move(\'' + mapCoord.destMapId + '\');" href="javascript:void(0);" alt="' + mapCoord.destMapName + '" title="' + mapCoord.destMapName + '"/>';
        }

        $('#map-coords').html(coordsHtml);
    },
    // 加载在线列表
    loadOnlineInfo: function(data){
        let mapCharacterList = data.onlineInfo.mapCharacterList;
        let mapMobList = data.onlineInfo.mapMobList;
        // 更新在线列表
        $('#online-all').html('');
        $('#online-player').html('');
        $('#online-mob').html('');
        for (let index in mapCharacterList) {
            let mapCharacter = mapCharacterList[index];
            let row = '<div class="layui-row"><div class="layui-col-md9"><label style="color: blue;">' + mapCharacter.name + '</label><label> - 等级：' + mapCharacter.level + '</label></div><div class="layui-col-md3"><button type="button" style="height:14px;line-height: 14px;">查看</button><button type="button" style="height:14px;line-height: 14px;" onclick="whisper(\'' + mapCharacter.id + '\', \'' + mapCharacter.name + '\')">私聊</button></div></div>';
            $('#online-all').append(row);
            $('#online-player').append(row);
        }

        for (let index in mapMobList) {
            let mapMob = mapMobList[index];
            let row = '<div class="layui-row"><div class="layui-col-md9"><label style="color: red;">' + mapMob.name + '</label><label> - 等级：' + mapMob.level + '</label></div><div class="layui-col-md3"><button type="button" style="height:14px;line-height: 14px;" onclick="battleMob(\'' + mapMob.id + '\');">战斗</button><button type="button" style="height:14px;line-height:14px;" onclick="guaji();">挂机</button></div></div>';
            $('#online-all').append(row);
            $('#online-mob').append(row);
        }
    },
    // 加载聊天消息
    loadChat: function(data){
        let channelText = "";
        let content = "";
        if (data.channel === ChatChannel.Local) {
            channelText = "【本地】";
        } else if (data.channel === ChatChannel.World) {
            channelText = "【世界】";
        } else if (data.channel === ChatChannel.Whisper) {
            channelText = "【私聊】";
        }

        if (data.channel === ChatChannel.Whisper){
            let sendName = data.sendId === this.character.id ? "你" : data.sendName;
            let recvName = data.sendId === this.character.id ? data.recvName : "你";
            content = "<p style=\"background: #009688;opacity: 0.7;\"><span>" + channelText + "</span>" + sendName + " 悄悄对 " + recvName + "说：" + data.message + "</p>";
        } else {
            content = "<p><span>" + channelText + "</span>" + data.sendName + "：" + data.message + "</p>";
        }

        $('.msg-chat').append(content);
    },
    // 加载在线打怪战况
    loadBattleMobResult: async function (data) {
        let that = this;
        $('.msg-battle').html('');
        let rounds = data.battleMobResult.roundList;
        if (data.battleMobResult.totalRound > 0) {
            for (let i = 0; i < rounds.length; i++) {
                let round = rounds[i];
                let content = "<p>【第" + round.number + "回合】</p>";
                for (let j = 0; j < round.messages.length; j++) {
                    content += "<p>" + round.messages[j] + "</p>";
                }

                content += "<hr />";
                $('.msg-battle').append(content);
                await this.sleep(1500);
            }

            $('.msg-battle').append("<p><strong>" + data.battleMobResult.resultMessage + "</strong></p>");
            if (data.battleMobResult.playerWin) {
                that.sendLoadCharacter();
            }

            if (that.isBattle) {
                that.battleInterval = setTimeout(function () {
                    that.sendBattleMob(that.battleMobId);
                }, 5000);
            }

            // await this.sleep(5000).then(function () {
            //     that.sendBattleMob(data.battleMobResult.mobId);
            // });
        }
    },

    //////////////////
    //// 发送消息 ////
    //////////////////

    // 读取缓存
    sendLoadCache: function () {
        let request = new RequestMessage();
        request.loadCache();
        this.socketHub.send(request);
    },
    // 登陆
    sendLogin: function () {
        let request = new RequestMessage();
        request.login();
        this.socketHub.send(request);
    },
    // 加载地图信息
    sendLoadMap: function () {
        let request = new RequestMessage();
        request.loadMap();
        this.socketHub.send(request);
    },
    // 加载在线列表
    sendLoadOnline: function () {
        let request = new RequestMessage();
        request.loadOnline();
        this.socketHub.send(request);
    },
    // 加载角色信息
    sendLoadCharacter: function() {
        let request = new RequestMessage();
        request.loadCharacter();
        this.socketHub.send(request);
    },
    // 发送聊天
    sendChat: function (param) {
        let request = new RequestMessage();
        request.chat(param);
        this.socketHub.send(request);
    },
    // 地图移动
    sendMove: function (mapId) {
        let request = new RequestMessage();
        request.move(mapId);
        this.socketHub.send(request);
    },
    // 在线打怪
    sendBattleMob: function (mobId) {
        let request = new RequestMessage();
        request.battleMob(mobId);
        this.socketHub.send(request);
    },

    //////////////////
    //// 辅助方法 ////
    //////////////////

    // 停止战斗
    stopBattle: function() {
        let that = this;
        if (that.isBattle) {
            that.isBattle = false;
            clearInterval(that.battleInterval);
        }
    },
    // 战斗结算
    settlement: function (battleResult) {
        $('.lbl-level').html(battleResult.settleLevel);
        $('.lbl-exp').html(battleResult.settleExp);
    },
    // 休眠
    sleep: function (milliseconds) {
        let p = new Promise(function (resolve) {
            setTimeout(function () {
                resolve();
            }, milliseconds)
        });
        return p;
    },
    // 关闭
    close: function () {
        this.socketHub.close();
    }
};


// wow客户端
window.wowClient = new WowClient();

// 关闭窗口
window.onbeforeunload = function (event) {
    wowClient.close();
};

document.onkeydown = function (event) {
    let e = event || window.event || arguments.callee.caller.arguments[0];
    if (e.keyCode === 13 && document.activeElement.id === 'chat-msg') {
        chat();
    }
};

/* 地图移动 */
function move(mapId) {
    let diff = new Date().getTime() - wowClient.moveTime;
    if (diff < TimeLag.Move * 1000) {
        let waitSeconds = parseInt(TimeLag.Move - diff / 1000);
        alert(waitSeconds + '秒后可再次移动！');
        return;
    }

    wowClient.sendMove(mapId);
}

/* 改变聊天频道 */
function changeChatChannel() {
    let channel = $('#chat-channel').val();
    if (channel === ChatChannel.Whisper) {;

    } else {
        $('#chat-whisperId').val('');
        $('#chat-whisperName').val('所有人');
    }
}

/* 私聊 */
function whisper(id, name) {
    if (id === wowClient.character.id) {
        alert('不能对自己私聊！');
    } else {
        $('#chat-channel').val('5');
        $('#chat-whisperId').val(id);
        $('#chat-whisperName').val(name);
    }
}

/* 发送聊天 */
function chat() {
    let channel = $('#chat-channel').val();
    let recvId = (channel === ChatChannel.Whisper ? $('#chat-whisperId').val() : "");
    let recvName = (channel === ChatChannel.Whisper ? $('#chat-whisperName').val() : "");
    let message = $('#chat-msg').val();
    let param = {
        recvId: recvId,
        recvName: recvName,
        channel: channel,
        message: message
    };

    wowClient.sendChat(param);
}

/* 在线打怪 */
function battleMob(mobId) {
    let diff = new Date().getTime() - wowClient.battleMobTime;
    if (diff < TimeLag.BattleMob * 1000) {
        let waitSeconds = parseInt(TimeLag.BattleMob - diff / 1000);
        alert('请勿频繁点击！' + waitSeconds + '秒后可再操作！');
        return;
    }

    if (mobId === wowClient.battleMobId) {
        alert("已经在战斗中！请勿重复点击！");
        return;
    }

    wowClient.battleMobId = mobId;
    wowClient.battleMobTime = new Date().getTime();
    if (!wowClient.isBattle) {
        wowClient.isBattle = true;
        wowClient.sendBattleMob(mobId);
    }
}


