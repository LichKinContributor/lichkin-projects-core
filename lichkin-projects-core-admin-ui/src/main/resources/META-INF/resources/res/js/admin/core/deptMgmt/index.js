var deptMgmtFormPlugins = [
    {
      plugin : 'textbox',
      options : {
        name : 'deptName',
        validator : true,
        maxlength : 64
      }
    }, {
      plugin : 'textbox',
      options : {
        name : 'description',
        validator : true,
        rows : 3,
        maxlength : 128
      }
    }
];

LK.UI.datagrid($.extend((typeof LK.home == 'undefined' ? {
  title : 'title',
  icon : 'deptMgmt',
} : {}), {
  i18nKey : 'deptMgmt',
  $appendTo : true,
  url : '/SysDept/S',
  cols : 3,
  pageable : false,
  treeFieldName : 'deptName',
  columns : [
      {
        text : 'deptName',
        width : '1/2',
        name : 'deptName'
      }, {
        text : 'description',
        width : '1/2',
        name : 'description'
      }
  ],
  toolsAdd : {
    saveUrl : '/SysDept/I',
    dialog : {
      size : {
        cols : 1,
        rows : 4
      }
    },
    form : {
      plugins : deptMgmtFormPlugins
    },
    beforeSave : function($button, $datagrid, $selecteds, selectedDatas, value, $dialogButton, $dialog) {
      if (selectedDatas.length == 0) {
        return {
          parentCode : 'ROOT'
        };
      } else {
        return {
          parentCode : selectedDatas[0].code
        };
      }
    }
  },
  toolsEdit : {
    saveUrl : '/SysDept/U',
    dialog : {
      size : {
        cols : 1,
        rows : 4
      }
    },
    form : {
      url : '/SysDept/O',
      plugins : deptMgmtFormPlugins
    }
  },
  toolsRemove : {
    saveUrl : '/SysDept/US',
    beforeClick : function($button, $datagrid, $selecteds, selectedDatas, value, i18nKey) {
      if (selectedDatas[0].children.length != 0) {
        LK.alert(i18nKey + 'this department has sub department');
        return false;
      }
      return true;
    }
  },
  toolsView : {
    dialog : {
      size : {
        cols : 1,
        rows : 4
      }
    },
    form : {
      url : '/SysDept/O',
      plugins : deptMgmtFormPlugins
    }
  },
  tools : [
      {
        singleCheck : true,
        icon : 'up',
        text : 'up',
        click : function($button, $plugin, $selecteds, selectedDatas, value) {
          LK.ajax({
            url : '/SysDept/S01',
            data : {
              up : true,
              deptCode : $selecteds.data().code,
            },
            showSuccess : true,
            success : function() {
              $plugin.LKLoad();
            }
          });
        }
      }, {
        singleCheck : true,
        icon : 'down',
        text : 'down',
        click : function($button, $plugin, $selecteds, selectedDatas, value) {
          LK.ajax({
            url : '/SysDept/S01',
            data : {
              up : false,
              deptCode : $selecteds.data().code
            },
            showSuccess : true,
            success : function() {
              $plugin.LKLoad();
            }
          });
        }
      }
  ],
  searchForm : [
    {
      plugin : 'textbox',
      options : {
        name : 'deptName',
        cls : 'fuzzy-left fuzzy-right'
      }
    }
  ]
}));