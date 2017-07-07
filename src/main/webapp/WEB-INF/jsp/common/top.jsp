<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="navbar navbar-inverse navbar-fixed-top animated fadeInDown">
      <div class="navbar-header">
          <button data-target=".navbar-collapse" data-toggle="collapse" type="button" class="navbar-toggle collapsed">
              <span class="sr-only">导航</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
          </button>
      </div>
      <div role="navigation" class="navbar-collapse collapse">
          <a id="_logo"  href="${baseurl}" style="color:#fff; font-size: 24px;" class="navbar-brand hidden-sm">泊车管理平台</a>
          <ul class="nav navbar-nav  pull-right" >
<!--              <shiro:hasAnyRoles name='SYS_ADMIN,SYS_ROLE_PERMISSION'> -->
              <li class="dropdown " style="color:#fff;">
                  <!--已经登录（包括记住我的）-->
                  <shiro:user>
                      <a aria-expanded="false" aria-haspopup="true"  role="button" data-toggle="dropdown" onclick="location.href='${baseurl}user/index.tkm'" href="${baseurl}/user/index.tkm" class="dropdown-toggle qqlogin" >
                          <shiro:principal property="nickname"/>
                          <span class="caret"></span></a>
                      <ul class="dropdown-menu" userid="<shiro:principal property="id"/>">
                          <li><a href="${baseurl}static/index.html#personal-data" target="mainframe">个人资料</a></li>
                          <li><a href="${baseurl}static/index.html#my-permission" target="mainframe">我的权限</a></li>
                          <li><a href="${baseurl}user/logout.tkm">退出登录</a></li>
                      </ul>
                  </shiro:user>
                    </li>
<!--                 </shiro:hasAnyRoles> -->
         </ul>
         <style>#topMenu>li>a{padding:10px 13px}</style>
      </div>
</div>
<aside class="main-sidebar">
    <ul id="menu-list">
        <li class="active">
            <a href="#"><i class="fa fa-tachometer"></i> <em>个人中心</em></a>
            <ul class="submenu">
                <li>
                    <a href="${baseurl}static/index.html#personal-data" target="mainframe"><i class="fa fa-circle-o"></i>个人资料</a>
                </li>
                <li>
                    <a href="${baseurl}static/index.html#info-modify" target="mainframe"><i class="fa fa-circle-o"></i>资料修改</a>
                </li>
                <li>
                    <a href="${baseurl}static/index.html#pdw-modify" target="mainframe"><i class="fa fa-circle-o"></i>密码修改</a>
                </li>
                <li>
                    <a href="${baseurl}static/index.html#my-permission" target="mainframe"><i class="fa fa-circle-o"></i>我的权限</a>
                </li>
            </ul>
        </li>
        <shiro:hasAnyRoles name='SYS_ADMIN,SYS_USER'>
        <li>
            <a href="#"><i class="fa fa-th"></i> <em>推送中心</em></a>
            <ul class="submenu">
                <shiro:hasPermission name="push:index">
                <li>
                    <a href="${baseurl}static/index.html#message-push" target="mainframe"><i class="fa fa-circle-o"></i>消息推送</a>
                </li>
                </shiro:hasPermission>
            </ul>
        </li>
        </shiro:hasAnyRoles>
        <shiro:hasAnyRoles name='SYS_ADMIN,SYS_USER'>
        <li>
            <a href="#"><i class="fa fa-table"></i> <em>用户中心</em></a>
            <ul class="submenu">
                <shiro:hasPermission name="member:list">
                  <li>
                      <a href="${baseurl}static/index.html#member-list" target="mainframe"><i class="fa fa-circle-o"></i>用户列表</a>
                  </li>
                </shiro:hasPermission>
            </ul>
        </li>
        </shiro:hasAnyRoles>
        <shiro:hasAnyRoles name='SYS_ADMIN,SYS_ROLE_PERMISSION'>
        <li>
            <a href="#"><i class="fa fa-table"></i> <em>权限管理</em></a>
            <ul class="submenu">
                <shiro:hasPermission name="role:index">
                  <li>
                      <a href="${baseurl}static/index.html#role-list" target="mainframe"><i class="fa fa-circle-o"></i>角色列表</a>
                  </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="role:allocation">
                  <li>
                      <a href="${baseurl}static/index.html#role-allocation" target="mainframe"><i class="fa fa-circle-o"></i>角色分配</a>
                  </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="permission:index">
                  <li>
                      <a href="${baseurl}static/index.html#permission-list" target="mainframe"><i class="fa fa-circle-o"></i>权限列表</a>
                  </li>
                </shiro:hasPermission>
                <shiro:hasPermission name="permission:allocation">
                  <li>
                      <a href="${baseurl}static/index.html#permission-allocation" target="mainframe"><i class="fa fa-circle-o"></i>权限分配</a>
                  </li>
                </shiro:hasPermission>
            </ul>
        </li>
        </shiro:hasAnyRoles>
        <shiro:hasAnyRoles name='SYS_ADMIN,SYS_ROLE_PERMISSION'>
        <li>
            <a href="#"><i class="fa fa-table"></i> <em>测试页</em></a>
            <ul class="submenu">
                <shiro:hasPermission name="role:index">
                  <li>
                      <a href="${baseurl}static/index.html#hello2" target="mainframe"><i class="fa fa-circle-o"></i>vue测试</a>
                  </li>
                </shiro:hasPermission>
            </ul>
        </li>
        </shiro:hasAnyRoles>
        
    </ul>
</aside>