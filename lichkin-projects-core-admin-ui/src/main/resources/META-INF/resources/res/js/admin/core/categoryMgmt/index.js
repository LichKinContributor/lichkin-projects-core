var categoryMgmtFormPlugins = [
    {
      plugin : 'droplist',
      options : {
        name : 'locale',
        param : {
          categoryCode : 'LOCALE'
        },
        validator : true,
      }
    }, {
      plugin : 'textbox',
      options : {
        name : 'categoryName',
        validator : true,
        maxlength : 64
      }
    }, {
      plugin : 'textbox',
      options : {
        name : 'categoryCode',
        validator : true,
        maxlength : 64
      }
    }, {
      plugin : 'droplist',
      options : {
        name : 'authType',
        validator : true,
        param : {
          categoryCode : 'CATEGORY_AUTH_TYPE'
        },
        value : 'ROOT',
        cancelable : false,
      }
    }, {
      plugin : 'numberspinner',
      options : {
        name : 'sortId',
        value : 0,
        min : 1,
        max : 127
      }
    }
];

LK.UI.datagrid($.extend((typeof LK.home == 'undefined' ? {
  title : 'title',
  icon : 'categoryMgmt',
} : {}), {
  i18nKey : 'categoryMgmt',
  $appendTo : true,
  cols : 4,
  url : '/SysCategory/L',
  pageable : false,
  columns : [
      {
        text : 'locale',
        width : 80,
        name : 'locale'
      }, {
        text : 'categoryName',
        width : '1/2',
        name : 'categoryName'
      }, {
        text : 'categoryCode',
        width : '1/2',
        name : 'categoryCode'
      }, {
        text : 'authType',
        width : 100,
        name : 'authType',
        cssClass : function($plugin, $container, data, level, options, columns, treeFieldName, i18nKey, text) {
          switch (data.authTypeDictCode) {
            case 'ENUM':
              return 'danger';
            case 'ROOT':
              return 'warning';
            case 'R_2_C':
              return 'primary';
            case 'COMP':
              return 'info';
            case 'COMMON':
              return 'success';
          }
        }
      }, {
        text : 'sortId',
        width : 60,
        name : 'sortId'
      }
  ],
  toolsAdd : {
    saveUrl : '/SysCategory/I',
    dialog : {
      size : {
        cols : 1,
        rows : 5
      }
    },
    form : {
      plugins : categoryMgmtFormPlugins
    }
  },
  toolsEdit : {
    saveUrl : '/SysCategory/U',
    dialog : {
      size : {
        cols : 1,
        rows : 5
      }
    },
    form : {
      plugins : categoryMgmtFormPlugins,
      url : '/SysCategory/O'
    },
    readonlyPlugins : function() {
      return [
          'locale', 'categoryCode'
      ];
    }
  },
  searchForm : [
      {
        plugin : 'hidden',
        options : {
          name : 'showFull',
          value : true
        }
      }, {
        plugin : 'droplist',
        options : {
          name : 'locale',
          param : {
            categoryCode : 'LOCALE'
          },
        }
      }, {
        plugin : 'textbox',
        options : {
          name : 'categoryName',
          cls : 'fuzzy-left fuzzy-right'
        }
      }, {
        plugin : 'textbox',
        options : {
          name : 'categoryCode',
          cls : 'fuzzy-left fuzzy-right'
        }
      }, {
        plugin : 'droplist',
        options : {
          name : 'authType',
          param : {
            categoryCode : 'CATEGORY_AUTH_TYPE'
          }
        }
      }
  ]
}));
