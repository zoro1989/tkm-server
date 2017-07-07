/**
 * Created by Administrator on 2017/3/16.
 */
require.config({

    paths : {
        // jQuery文件。务必在bootstrap.min.js 之前引入
        "jquery": ["http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min","../../lib/jquery-3.1.1"],
        "jquery-form": ["http://cdn.bootcss.com/jquery.form/4.2.0/jquery.form.min"],
        "layer": '../../common/layer/layer',
        "common": '../../common/common',
        // 最新的 Bootstrap 核心 JavaScript 文件
        "bootstrap": 'http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min',
        "bootstrap-treeview": 'http://cdn.bootcss.com/bootstrap-treeview/1.2.0/bootstrap-treeview.min',
        "bootstrap-select": 'https://cdn.bootcss.com/bootstrap-select/2.0.0-beta1/js/bootstrap-select.min',
        "bootstrap-select-CN": 'https://cdn.bootcss.com/bootstrap-select/2.0.0-beta1/js/i18n/defaults-zh_CN.min',
        // js操作cookie
        "cookie": 'http://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min',
        <!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
        "html5shiv": 'https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv',
        "respond": 'https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min',
        "countdown": 'http://cdn.bootcss.com/jquery-countdown/2.0.2/jquery.countdown.min',
        "MD5": 'http://open.itboy.net/common/MD5',
        "supersized": 'http://open.itboy.net/itboy/js/itboy/app/supersized.3.2.7.min',
        "supersized-init": 'http://open.itboy.net/itboy/js/itboy/app/supersized-init',
        // menu菜单
        "menu": '../../common/menu'
    },
    shim : {
        "jquery-form":["jquery"],
        "bootstrap":["jquery"],
        "bootstrap-treeview":["jquery","bootstrap"],
        "bootstrap-select":["jquery","bootstrap"],
        "bootstrap-select-CN":["jquery","bootstrap",'bootstrap-select'],
        "supersized":["jquery"],
        "supersized-init":["jquery","supersized"],
        "common":["jquery","menu"],
        "layer" : ["jquery"],
        "menu" : ["jquery"]
    }

});