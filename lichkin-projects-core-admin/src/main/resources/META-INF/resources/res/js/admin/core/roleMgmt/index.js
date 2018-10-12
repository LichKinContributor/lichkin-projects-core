var roleMgmtFormPlugins = [
    {
      plugin : 'textbox',
      options : {
        name : 'roleName',
        validator : true,
        maxlength : 64
      }
    }, {
      plugin : 'textbox',
      options : {
        name : 'description',
        validator : true,
        maxlength : 128
      }
    }, {
      plugin : 'tree',
      options : {
        key : 'menuName',
        name : 'menuIds',
        validator : true,
        url : '/AssignableMenus',
        onBeforeAddDatas : function($plugin, responseDatas, url, param) {
          return responseDatas;
        }
      }
    }
];

LK.UI.datagrid($.extend((typeof LK.home == 'undefined' ? {
  title : 'title',
  icon : 'roleMgmt',
} : {}), {
  i18nKey : 'roleMgmt',
  $appendTo : true,
  cols : 2.5,
  rows : 10,
  url : '/SysRole/P',
  columns : [
      {
        text : 'roleName',
        name : 'roleName',
        width : '1/3'
      }, {
        text : 'description',
        name : 'description',
        width : '2/3'
      }
  ],
  toolsAdd : {
    saveUrl : '/SysRole/I',
    dialog : {
      size : {
        cols : 2,
        rows : 11
      }
    },
    form : {
      plugins : roleMgmtFormPlugins
    }
  },
  toolsEdit : {
    saveUrl : '/SysRole/U',
    dialog : {
      size : {
        cols : 2,
        rows : 11
      }
    },
    form : {
      url : '/SysRole/O',
      plugins : roleMgmtFormPlugins
    }
  },
  toolsRemove : {
    saveUrl : '/SysRole/US'
  },
  toolsView : {
    dialog : {
      size : {
        cols : 2,
        rows : 11
      }
    },
    form : {
      url : '/SysRole/O',
      plugins : roleMgmtFormPlugins
    }
  },
  searchForm : [
    {
      plugin : 'textbox',
      options : {
        name : 'roleName',
        cls : 'fuzzy-left fuzzy-right'
      }
    }
  ]
}));