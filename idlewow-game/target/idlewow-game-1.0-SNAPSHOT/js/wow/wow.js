var EnumUtil = function () {
};

EnumUtil.prototype = {
    race: function(value){
      switch(value){
          case '10201':
              return '暗夜精灵';
          case '10202':
              return '人类';
          case '10203':
              return '兽人';
          case '10204':
              return '牛头人';
          case '10205':
              return '矮人';
          case '10206':
              return '亡灵';
          case '10207':
              return '巨魔';
          case '10208':
              return '侏儒';
          default:
              return value;
      }
    },
    job: function (value) {
        switch (value) {
            case '10251':
                return '<span>战士</span>';
            case '10252':
                return '<span>法师</span>';
            case '10253':
                return '<span>猎人</span>';
            case '10254':
                return '<span>牧师</span>';
            case '10255':
                return '<span>盗贼</span>';
            case '10256':
                return '<span>德鲁伊</span>';
            case '10257':
                return '<span>术士</span>';
            case '10258':
                return '<span>萨满</span>';
            case '10259':
                return '<span>圣骑士</span>';
            default:
                return '';
        }
    },
    faction: function (value) {
        switch (value) {
            case '10111':
                return '<span>联盟</span>';
            case '10112':
                return '<span>部落</span>';
            case '10113':
                return '<span>生物</span>';
            case '10114':
                return '<span>蜘蛛</span>';
            case '10115':
                return '<span>狼人</span>';
            case '10116':
                return '<span>食腐鸟类</span>';
            default:
                return '';
        }
    },
    mobClass: function (value) {
        switch (value) {
            case 0:
                return '<span>未指定</span>';
            case 1:
                return '<span>野兽</span>';
            case 2:
                return '<span>小动物</span>';
            case 3:
                return '<span>恶魔</span>';
            case 4:
                return '<span>龙类</span>';
            case 5:
                return '<span>元素生物</span>';
            case 6:
                return '<span>巨人</span>';
            case 7:
                return '<span>人型生物</span>';
            case 8:
                return '<span>机械</span>';
            case 9:
                return '<span>亡灵</span>';
            default:
                return '';
        }
    },
    mobType: function (value) {
        switch (value) {
            case 1:
                return '<span>普通</span>';
            case 2:
                return '<span>稀有</span>';
            case 3:
                return '<span>精英</span>';
            case 4:
                return '<span>世界首领</span>';
            default:
                return '';
        }
    }
};

window.enumUtil = new EnumUtil();

