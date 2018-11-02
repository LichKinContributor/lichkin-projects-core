LK.UI.datagrid($.extend((typeof LK.home == 'undefined' ? {
  title : 'title',
  icon : 'userMgmt',
} : {}), {
  i18nKey : 'userMgmt',
  $appendTo : true,
  cols : 4,
  url : '/SysUserLogin/P',
  columns : [
      {
        text : 'loginName',
        width : 200,
        name : 'loginName'
      }, {
        text : 'cellphone',
        width : 100,
        name : 'cellphone'
      }, {
        text : 'email',
        width : 180,
        name : 'email'
      }, {
        text : 'userName',
        width : 80,
        name : 'userName'
      }, {
        text : 'gender',
        width : 60,
        name : 'gender'
      }, {
        text : 'level',
        width : 60,
        name : 'level'
      }, {
        text : 'insertTime',
        width : 160,
        formatter : function(rowData) {
          return showStandardTime(rowData.insertTime);
        }
      }
  ],
  pageable : true,
  searchForm : [
      {
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
          name : 'gender',
          param : {
            categoryCode : 'GENDER',
            includes : [
                'MALE', 'FEMALE', 'OTHER'
            ].join(LK.SPLITOR)
          }
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
