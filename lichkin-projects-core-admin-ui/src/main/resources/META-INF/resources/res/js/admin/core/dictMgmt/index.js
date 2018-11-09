var dictMgmtFormPlugins = [
    {
      plugin : 'textbox',
      options : {
        name : 'dictName',
        validator : true,
        maxlength : 64
      }
    }, {
      plugin : 'textbox',
      options : {
        name : 'dictCode',
        validator : true,
        maxlength : 64
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

LK.UI.datagrid({
  i18nKey : 'dictMgmt.grid-category',
  $appendTo : $('#dictMgmt'),
  name : 'category',
  cols : 2.5,
  rows : 18,
  withField : false,
  linkages : [
    'dictionary'
  ],
  url : '/SysCategory/L',
  valueFieldName : 'categoryCode',
  columns : [
    {
      text : 'categoryName',
      width : null,
      formatter : function(data) {
        if (data.root) {
          return data.locale + ' -> ' + data.authTypeDictCode + ' -> ' + data.categoryCode + ' -> ' + data.categoryName;
        }
        return data.categoryName;
      },
      cssClass : function($plugin, $container, data, level, options, columns, treeFieldName, i18nKey, text) {
        if (data.root) {
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
        return null;
      },
      columnStyler : function($plugin, $container, data, level, options, columns, treeFieldName, i18nKey, text) {
        if (data.root) {
          return {
            'text-align' : 'left'
          };
        }
        return {
          'text-align' : 'center'
        };
      }
    }
  ],
  title : 'title',
  pageable : false,
  cancelable : false
});

LK.UI.datagrid({
  i18nKey : 'dictMgmt.grid-dictionary',
  $appendTo : $('#dictMgmt'),
  name : 'dictionary',
  cols : 4.5,
  rows : 18,
  withField : false,
  showSearchButton : false,
  onLinkaged : function($plugin, linkage) {
    switch (linkage.linkageName) {
      case 'category':
        if (linkage.linkageValue == '') {
          $plugin.LKClearDatas();
        } else {
          $plugin.LKLoad({
            param : {
              categoryCode : linkage.linkageValue
            }
          }, linkage);
        }
        break;
    }
  },
  reloadParam : function($plugin, param) {
    param.categoryCode = $plugin.LKGetSiblingPlugin('category').LKGetValue();
    return param;
  },
  url : '/SysDictionary/L',
  title : 'title',
  columns : [
      {
        text : 'dictName',
        width : '1/2',
        name : 'dictName'
      }, {
        text : 'dictCode',
        width : '1/2',
        name : 'dictCode'
      }, {
        text : 'sortId',
        width : 80,
        name : 'sortId'
      }
  ],
  pageable : false,
  toolsAdd : {
    titleTools : true,
    beforeClick : function($button, $datagrid, $selecteds, selectedDatas, value) {
      var categoryCode = $datagrid.LKGetSiblingPlugin('category').LKGetValue();
      if (categoryCode == '') {
        LK.alert('noCategorySelect');
        return false;
      }
      return true;
    },
    beforeSave : function($button, $datagrid, $selecteds, selectedDatas, value, $dialogButton, $dialog) {
      return {
        categoryCode : $datagrid.LKGetSiblingPlugin('category').LKGetValue()
      };
    },
    saveUrl : '/SysDictionary/I',
    dialog : {
      size : {
        cols : 1,
        rows : 3
      }
    },
    form : {
      plugins : dictMgmtFormPlugins
    }
  },
  toolsEdit : {
    titleTools : true,
    saveUrl : '/SysDictionary/U',
    dialog : {
      size : {
        cols : 1,
        rows : 3
      }
    },
    form : {
      url : '/SysDictionary/O',
      plugins : dictMgmtFormPlugins
    },
    readonlyPlugins : function() {
      return [
        'dictCode'
      ];
    }
  },
  toolsRemove : {
    titleTools : true,
    saveUrl : '/SysDictionary/US'
  },
  leftBorder : true
});
