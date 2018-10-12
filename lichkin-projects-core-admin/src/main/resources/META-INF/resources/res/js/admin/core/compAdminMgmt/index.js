var compAdminMgmtAddFormPlugins = [
    {
      plugin : 'cropper',
      options : {
        key : 'photo',
        name : 'photo',
        compressWidth : 512,
        compressHeight : 512,
        cols : 1,
        rows : 4
      }
    }, {
      plugin : 'droplist',
      options : {
        key : 'compName',
        name : 'busCompId',
        url : '/SysComp/LD',
        validator : true,
        cancelable : false,
        linkages : [
          'userName'
        ]
      }
    }, {
      plugin : 'textbox',
      options : {
        name : 'email',
        validator : 'required,email',
        maxlength : 128
      }
    }, {
      plugin : 'textbox',
      options : {
        name : 'userName',
        validator : true,
        maxlength : 64,
        onLinkaged : function($plugin, linkage) {
          switch (linkage.linkageName) {
            case 'busCompId':
              if (linkage.linkageValue == '') {
              } else {
                var datas = linkage.$linkage.data('LKDatas');
                for (var i = 0; i < datas.length; i++) {
                  if (datas[i].value == linkage.linkageValue) {
                    $plugin.LKInvokeSetValues(datas[i].text);
                    break;
                  }
                }
              }
              break;
          }
        }
      }
    }, {
      plugin : 'hidden',
      options : {
        name : 'gender',
        value : 'SECRECY'
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

var compAdminMgmtEditFormPlugins = $.extend(true, [], compAdminMgmtAddFormPlugins);
compAdminMgmtEditFormPlugins[1].options.linkages = [];
compAdminMgmtEditFormPlugins[1].options.readonly = true;

LK.UI.datagrid($.extend((typeof LK.home == 'undefined' ? {
  title : 'title',
  icon : 'compAdminMgmt',
} : {}), {
  i18nKey : 'compAdminMgmt',
  $appendTo : true,
  cols : 5,
  url : '/SysAdminLogin/P01',
  columns : [
      {
        text : 'compName',
        width : 200,
        name : 'compName'
      }, {
        text : 'email',
        width : 160,
        name : 'email'
      }, {
        text : 'userName',
        width : 200,
        name : 'userName'
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
      plugins : compAdminMgmtAddFormPlugins
    }
  },
  toolsEdit : {
    saveUrl : '/SysAdminLogin/U03',
    dialog : {
      size : {
        cols : 2,
        rows : 4
      }
    },
    form : {
      url : '/SysAdminLogin/O',
      plugins : compAdminMgmtEditFormPlugins
    }
  },
  toolsRemove : {
    saveUrl : '/SysAdminLogin/US'
  },
  toolsView : {
    dialog : {
      size : {
        cols : 2,
        rows : 4
      }
    },
    form : {
      url : '/SysAdminLogin/O',
      plugins : compAdminMgmtEditFormPlugins
    }
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
          name : 'compName',
          cls : 'fuzzy-left fuzzy-right'
        }
      }, {
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
