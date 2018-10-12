LK.UI.datagrid($.extend((typeof LK.home == 'undefined' ? {
  title : 'title',
  icon : 'loginLog',
} : {}), {
  i18nKey : 'loginLog',
  $appendTo : true,
  cols : 4,
  url : '/SysAdminLoginLog/P',
  multiSelect : true,
  columns : [
      {
        text : 'userName',
        width : 220,
        name : 'userName'
      }, {
        text : 'email',
        width : 180,
        name : 'email'
      }, {
        text : 'requestId',
        width : 260,
        name : 'requestId',
        cssClass : 'monospacedFont'
      }, {
        text : 'requestIp',
        width : 180,
        name : 'requestIp'
      }, {
        text : 'requestTime',
        width : 160,
        formatter : function(rowData) {
          return formatterTime(rowData.requestTime);
        }
      }
  ],
  pageable : true,
  searchForm : [
      {
        plugin : 'textbox',
        options : {
          name : 'userName',
          cls : 'fuzzy-left fuzzy-right'
        }
      }, {
        plugin : 'textbox',
        options : {
          name : 'email',
          cls : 'fuzzy-left fuzzy-right'
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
