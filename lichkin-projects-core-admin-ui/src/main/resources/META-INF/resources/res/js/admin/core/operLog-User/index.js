LK.UI.datagrid($.extend((typeof LK.home == 'undefined' ? {
  title : 'title',
  icon : 'operLog',
} : {}), {
  i18nKey : 'operLog-User',
  $appendTo : true,
  cols : 4,
  url : '/SysUserOperLog/P',
  columns : [
      {
        text : 'loginName',
        width : 220,
        name : 'loginName'
      }, {
        text : 'cellphone',
        width : 120,
        name : 'cellphone'
      }, {
        text : 'requestId',
        width : 260,
        name : 'requestId',
        cssClass : 'monospacedFont'
      }, {
        text : 'requestIp',
        width : 120,
        name : 'requestIp'
      }, {
        text : 'operType',
        width : 80,
        name : 'operType'
      }, {
        text : 'busType',
        width : null,
        name : 'busType'
      }, {
        text : 'requestTime',
        width : 160,
        formatter : function(rowData) {
          return showStandardTime(rowData.requestTime);
        }
      }
  ],
  pageable : true,
  searchForm : [
      {
        plugin : 'droplist',
        options : {
          name : 'operType',
          param : {
            categoryCode : 'OPERATION_TYPE'
          }
        }
      }, {
        plugin : 'textbox',
        options : {
          name : 'busType',
          cls : 'fuzzy-left fuzzy-right'
        }
      }, {
        plugin : 'textbox',
        options : {
          name : 'loginName',
          cls : 'fuzzy-left fuzzy-right'
        }
      }, {
        plugin : 'textbox',
        options : {
          name : 'cellphone',
          cls : 'fuzzy-right'
        }
      }, {
        plugin : 'datepicker',
        options : {
          name : 'startDate'
        }
      }, {
        plugin : 'datepicker',
        options : {
          name : 'endDate'
        }
      }
  ]
}));
