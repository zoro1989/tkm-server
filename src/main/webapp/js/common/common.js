
/**
 * 封装的Demo
 */
(function(o,w){
    if(!w.so)w.so = {};
    return (function(so){
        so.$1 = !0,//true
            so.$0 = !1;//false
        /**
         * 全选
         */
        so.checkBoxInit = function(prentCheckbox,childCheckbox){
            childCheckbox = o(childCheckbox),prentCheckbox = o(prentCheckbox);
            //先取消全选。
            //childCheckbox.add(prentCheckbox).attr('checked',!1);
            //全选
            prentCheckbox.on('click',function(){
                childCheckbox.attr('checked',this.checked);
            });
            //子选择
            childCheckbox.on('click',function(){
                prentCheckbox.attr('checked',childCheckbox.length === childCheckbox.end().find(':checked').not(prentCheckbox).length);
            });
        },
        //初始化
        so.init = function(fn){
            o(function(){fn()});
        }
        so.initMenu = function(){
        	var defaults = {
        			speed: 300,
        			headerHeight: 50
        		};
            	
        	var sidebar = $(".main-sidebar");
    		var mainHeight = $(window).height() - defaults.headerHeight;
    		
    		//菜单初始化
    		sidebar.jqueryAccordionMenu();
    		
    		//初始化菜单高度
//    		sidebar.height(mainHeight);
        	
//    		$(window).resize(function () {
//    			//改变窗口大小，重新计算菜单高度
//    			!function () {
//    				var sidebar = $(".main-sidebar");
//    				var mainHeight = $(window).height() - defaults.headerHeight;
//    				sidebar.height(mainHeight);
//    			}();
//    		});
    		
    		//菜单选中效果
    		$("#menu-list > li, #menu-list > li ul li").on("click", function () {
    			$("#menu-list li").removeClass("active");
    			$(this).addClass("active");
    			$(this).parents("li").addClass("active");
    		});
        }
        so.id = function(id){
            return o('#' + id);
        }
        so.default = function(){}

    })(so);
})($,window);
