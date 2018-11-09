var menuMgmtFormPlugins = [
    {
      plugin : 'textbox',
      options : {
        name : 'menuName',
        validator : true,
        maxlength : 64
      }
    }, {
      plugin : 'droplist',
      options : {
        name : 'rootOnly',
        data : [
            {
              text : $.LKGetI18N('ROOT_ONLY', 'true'),
              value : 'true'
            }, {
              text : $.LKGetI18N('ROOT_ONLY', 'false'),
              value : 'false'
            }, {
              text : $.LKGetI18N('ROOT_ONLY', 'null'),
              value : 'null'
            }
        ],
        cancelable : false,
        value : 'false'
      }
    }, {
      plugin : 'droplist',
      options : {
        name : 'assignable',
        data : [
            {
              text : $.LKGetI18N('ASSIGNABLE', 'true'),
              value : 'true'
            }, {
              text : $.LKGetI18N('ASSIGNABLE', 'false'),
              value : 'false'
            }
        ],
        cancelable : false,
        value : 'true'
      }
    }, {
      plugin : 'droplist',
      options : {
        name : 'onLine',
        data : [
            {
              text : $.LKGetI18N('ON_LINE', 'true'),
              value : 'true'
            }, {
              text : $.LKGetI18N('ON_LINE', 'false'),
              value : 'false'
            }
        ],
        cancelable : false,
        value : 'false'
      }
    }, {
      plugin : 'textbox',
      options : {
        name : 'icon',
        validator : true,
        maxlength : 32
      }
    }, {
      plugin : 'textbox',
      options : {
        name : 'url',
        maxlength : 128
      }
    }
];

LK.UI.datagrid($.extend((typeof LK.home == 'undefined' ? {
  title : 'title',
  icon : 'menuMgmt',
} : {}), {
  i18nKey : 'menuMgmt',
  $appendTo : true,
  url : '/SysMenu/T',
  cols : 5,
  pageable : false,
  treeFieldName : 'menuName',
  columns : [
      {
        text : 'menuName',
        width : '3/5',
        name : 'menuName'
      }, {
        text : 'menuName',
        width : 120,
        formatter : function(rowData) {
          return $.LKGetI18NWithPrefix('', rowData.params.menuName);
        },
        cssClass : function($plugin, $container, data, level, options, columns, treeFieldName, i18nKey, text) {
          if (data.params.rootOnly == null) {
            return 'success';
          } else {
            return data.params.rootOnly ? 'danger' : 'info';
          }
        }
      }, {
        text : 'rootOnly',
        width : 120,
        formatter : function(rowData) {
          return $.LKGetI18N('ROOT_ONLY', rowData.params.rootOnly);
        },
        cssClass : function($plugin, $container, data, level, options, columns, treeFieldName, i18nKey, text) {
          if (data.params.rootOnly == null) {
            return 'success';
          } else {
            return data.params.rootOnly ? 'danger' : 'info';
          }
        }
      }, {
        text : 'assignable',
        width : 80,
        formatter : function(rowData) {
          return $.LKGetI18N('ASSIGNABLE', rowData.params.assignable);
        },
        cssClass : function($plugin, $container, data, level, options, columns, treeFieldName, i18nKey, text) {
          return data.params.assignable ? 'primary' : 'warning';
        }
      }, {
        text : 'onLine',
        width : 80,
        formatter : function(rowData) {
          return $.LKGetI18N('ON_LINE', rowData.params.onLine);
        },
        columnStyler : function($plugin, $container, data, level, options, columns, treeFieldName, i18nKey, text) {
          if (data.params.onLine == true) {
            return {
              'background' : '#b4dcff'
            };
          } else {
            return {
              'background' : '#ffdcda'
            };
          }
        },
        columnTextStyler : function($plugin, $container, data, level, options, columns, treeFieldName, i18nKey, text) {
          if (data.params.onLine == true) {
            return {
              'color' : '#2e6da4'
            };
          } else {
            return {
              'color' : '#d43f3a'
            };
          }
        }
      }, {
        text : 'url',
        width : '2/5',
        name : 'url',
        textAlign : 'left',
      }
  ],
  toolsAdd : {
    saveUrl : '/SysMenu/I',
    dialog : {
      size : {
        cols : 1,
        rows : 6
      }
    },
    form : {
      plugins : menuMgmtFormPlugins
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
    saveUrl : '/SysMenu/U',
    dialog : {
      size : {
        cols : 1,
        rows : 6
      }
    },
    form : {
      url : '/SysMenu/O',
      plugins : menuMgmtFormPlugins
    }
  },
  tools : [
      {
        singleCheck : true,
        icon : 'up',
        text : 'up',
        click : function($button, $plugin, $selecteds, selectedDatas, value) {
          LK.ajax({
            url : '/SysMenu/Move',
            data : {
              up : true,
              menuCode : $selecteds.data().code,
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
            url : '/SysMenu/Move',
            data : {
              up : false,
              menuCode : $selecteds.data().code
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
          name : 'menuName',
          cls : 'fuzzy-left fuzzy-right'
        }
      }, {
        plugin : 'droplist',
        options : {
          name : 'rootOnly',
          data : [
              {
                text : $.LKGetI18N('ROOT_ONLY', 'true'),
                value : 'true'
              }, {
                text : $.LKGetI18N('ROOT_ONLY', 'false'),
                value : 'false'
              }, {
                text : $.LKGetI18N('ROOT_ONLY', 'null'),
                value : 'null'
              }
          ],
        }
      }, {
        plugin : 'droplist',
        options : {
          name : 'assignable',
          data : [
              {
                text : $.LKGetI18N('ASSIGNABLE', 'true'),
                value : 'true'
              }, {
                text : $.LKGetI18N('ASSIGNABLE', 'false'),
                value : 'false'
              }
          ],
        }
      }, {
        plugin : 'droplist',
        options : {
          name : 'onLine',
          data : [
              {
                text : $.LKGetI18N('ON_LINE', 'true'),
                value : 'true'
              }, {
                text : $.LKGetI18N('ON_LINE', 'false'),
                value : 'false'
              }
          ],
        }
      }
  ]
}));