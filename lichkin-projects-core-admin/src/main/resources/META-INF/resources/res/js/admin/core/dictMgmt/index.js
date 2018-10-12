var dictMgmtStyles = '';

dictMgmtStyles += '#dictionary .lichkin-datagrid .lichkin-datagrid-dataBar .lichkin-datagrid-dataHeaderBar .lichkin-table {margin-left:1px;}';

$('#lichkin-styles').html($('#lichkin-styles').html() + dictMgmtStyles);

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
        name : 'orderId',
        value : 0,
        min : 1,
        max : 127
      }
    }
];

LK.UI.datagrid({
  i18nKey : 'dictMgmt.grid-category',
  $appendTo : $('#category'),
  name : 'category',
  cols : 1,
  rows : 10,
  showSearchButton : false,
  linkages : [
    'dictionary'
  ],
  url : '/SysCategory/L',
  valueFieldName : 'categoryCode',
  columns : [
    {
      text : 'categoryName',
      width : LK.colWidth,
      formatter : function(rowData) {
        return rowData.categoryName + '<br>(' + rowData.categoryCode + ')';
      }
    }
  ],
  title : 'title',
  pageable : false,
  cancelable : false
});

LK.UI.datagrid({
  i18nKey : 'dictMgmt.grid-dictionary',
  $appendTo : $('#dictionary'),
  name : 'dictionary',
  cols : 3,
  rows : 10,
  showSearchButton : false,
  onLinkaged : function($plugin, linkage) {
    if (linkage.isCreateEvent == true) {
      return;
    }
    $plugin.LKLoad({
      param : {
        categoryCode : linkage.linkageCurrentValue
      }
    });
  },
  reloadParam : function($plugin, param) {
    param.categoryCode = $plugin.LKGetSiblingPlugin('category').LKGetValue();
    return param;
  },
  url : '/SysDictionary/L',
  title : 'title',
  columns : [
      {
        text : 'dictCode',
        width : '1/3',
        name : 'dictCode'
      }, {
        text : 'dictName',
        width : '1/3',
        name : 'dictName'
      }, {
        text : 'orderId',
        width : '1/3',
        name : 'orderId'
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
  toolsView : {
    titleTools : true,
    dialog : {
      size : {
        cols : 1,
        rows : 3
      }
    },
    form : {
      url : '/SysDictionary/O',
      plugins : dictMgmtFormPlugins
    }
  }
});
