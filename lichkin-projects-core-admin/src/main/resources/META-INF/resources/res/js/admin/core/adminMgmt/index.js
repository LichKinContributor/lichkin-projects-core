var adminMgmtFormPlugins = [
    {
      plugin : 'cropper',
      options : {
        name : 'photo',
        compressWidth : 512,
        compressHeight : 512,
        cols : 1,
        rows : 4
      }
    }, {
      plugin : 'textbox',
      options : {
        name : 'email',
        validator : 'required',
        maxlength : 16
      }
    }, {
      plugin : 'textbox',
      options : {
        name : 'userName',
        validator : true,
        maxlength : 64
      }
    }, {
      plugin : 'droplist',
      options : {
        name : 'gender',
        url : '/SysDictionary/LD',
        param : {
          categoryCode : 'GENDER',
          includes : [
              'MALE', 'FEMALE', 'SECRECY'
          ].join(LK.SPLITOR)
        },
        validator : true,
        cancelable : false
      }
    }, {
      plugin : 'droplist',
      options : {
        key : 'roleName',
        name : 'roleIds',
        validator : true,
        url : '/SysRole/LD',
        multiSelect : true
      }
    }
];

LK.UI.datagrid($.extend((typeof LK.home == 'undefined' ? {
  title : 'title',
  icon : 'adminMgmt',
} : {}), {
  i18nKey : 'adminMgmt',
  $appendTo : true,
  cols : 3,
  url : '/SysAdminLogin/P',
  columns : [
      {
        text : 'email',
        width : 160,
        name : 'email'
      }, {
        text : 'userName',
        width : 120,
        name : 'userName'
      }, {
        text : 'gender',
        width : 60,
        name : 'gender'
      }, {
        text : 'usingStatus',
        width : 100,
        name : 'usingStatus'
      }, {
        text : 'level',
        width : 60,
        name : 'level'
      }
  ],
  pageable : true,
  toolsAdd : {
    saveUrl : '/SysAdminLogin/I',
    dialog : {
      size : {
        cols : 2,
        rows : 4
      }
    },
    form : {
      plugins : adminMgmtFormPlugins
    }
  },
  toolsEdit : {
    saveUrl : '/SysAdminLogin/U',
    dialog : {
      size : {
        cols : 2,
        rows : 4
      }
    },
    form : {
      plugins : adminMgmtFormPlugins,
      url : '/SysAdminLogin/O'
    },
    readonlyPlugins : function() {
      return [
        'email'
      ];
    }
  },
  toolsView : {
    dialog : {
      size : {
        cols : 2,
        rows : 4
      }
    },
    form : {
      plugins : adminMgmtFormPlugins,
      url : '/SysAdminLogin/O'
    }
  },
  toolsRemove : {
    saveUrl : '/SysAdminLogin/US'
  },
  toolsUS : [
      {
        icon : 'reset',
        text : 'resetPwd',
        saveUrl : '/SysAdminLogin/U02',
        usingStatus : null,
      }, {
        icon : 'unlock',
        text : 'unlock',
        saveUrl : '/SysAdminLogin/US',
        usingStatus : 'USING',
        allowUsingStatusArr : [
          {
            usingStatus : 'LOCKED',
            errorMsg : 'unlock can only be performed when it is LOCKED!'
          }
        ]
      }, {
        icon : 'lock',
        text : 'lock',
        saveUrl : '/SysAdminLogin/US',
        usingStatus : 'LOCKED',
        allowUsingStatusArr : [
          {
            usingStatus : 'USING',
            errorMsg : 'lock can only be performed when it is USING!'
          }
        ]
      }
  ],
  searchForm : [
      {
        plugin : 'textbox',
        options : {
          name : 'email',
          cls : 'fuzzy-left fuzzy-right'
        }
      }, {
        plugin : 'textbox',
        options : {
          name : 'userName',
          cls : 'fuzzy-left fuzzy-right'
        }
      }, {
        plugin : 'droplist',
        options : {
          name : 'usingStatus',
          url : '/SysDictionary/LD',
          param : {
            categoryCode : 'USING_STATUS',
            includes : [
                'USING', 'LOCKED'
            ].join(LK.SPLITOR)
          }
        }
      }
  ]
}));
