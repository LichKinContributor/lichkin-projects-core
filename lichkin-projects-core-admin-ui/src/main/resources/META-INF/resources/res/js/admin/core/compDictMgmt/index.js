var compDictMgmtFormPlugins = [
    {
      plugin : 'textbox',
      options : {
        key : 'categoryName',
        name : 'categoryName',
        readonly : true,
      }
    }, {
      plugin : 'textbox',
      options : {
        key : 'categoryCode',
        name : 'categoryCode',
        readonly : true,
      }
    }, {
      plugin : 'textbox',
      options : {
        key : 'dictName',
        name : 'dictName',
        readonly : true,
      }
    }, {
      plugin : 'textbox',
      options : {
        key : 'dictCode',
        name : 'dictCode',
        readonly : true,
      }
    }, {
      plugin : 'datagrid',
      options : {
        i18nKey : 'compDictMgmt.comp-grid',
        key : 'title',
        name : 'compGrid',
        cols : 3,
        rows : 10,
        withoutFieldKey : true,
        url : '/SysDictionary/L02',
        pageable : false,
        showSearchButton : false,
        showResetButton : false,
        $appendTo : $body,
        columns : [
          {
            text : 'compName',
            width : null,
            name : 'compName'
          }
        ],
        toolsAdd : {
          saveUrl : '/SysDictionary/I',
          dialog : {
            size : {
              cols : 1,
              rows : 1
            }
          },
          form : {
            plugins : [
              {
                plugin : 'droplist',
                options : {
                  key : 'compName',
                  name : 'compId',
                  url : '/ROOT_GetCompLD',
                  validator : true,
                  cancelable : false,
                }
              }
            ]
          },
        },
        toolsRemove : {
          saveUrl : '/SysDictionary/S'
        }
      }
    }
];

LK.UI.datagrid($.extend((typeof LK.home == 'undefined' ? {
  title : 'title',
  icon : 'compDictMgmt',
} : {}), {
  i18nKey : 'compDictMgmt',
  $appendTo : true,
  cols : 4,
  url : '/SysDictionary/L01',
  columns : [
      {
        text : 'categoryName',
        width : '1/4',
        name : 'categoryName'
      }, {
        text : 'categoryCode',
        width : '1/4',
        name : 'categoryCode'
      }, {
        text : 'dictName',
        width : '1/4',
        name : 'dictName'
      }, {
        text : 'dictCode',
        width : '1/4',
        name : 'dictCode'
      }
  ],
  pageable : false,
  toolsEdit : {
    hideButtons : true,
    formValues : function($button, $datagrid, $selecteds, selectedDatas, value, i18nKey) {
      return selectedDatas;
    },
    handleFormOptions : function(editJson, formOptions, $datagrid, $selecteds, selectedDatas, value, i18nKey) {
      LK.UI.formUtils.changeOptions(formOptions.plugins, 'compGrid', false, {
        toolsAdd : {
          beforeSave : function() {
            return selectedDatas;
          }
        },
        searchForm : [
            {
              plugin : 'hidden',
              options : {
                name : 'locale',
                value : selectedDatas.locale
              }
            }, {
              plugin : 'hidden',
              options : {
                name : 'categoryCode',
                value : selectedDatas.categoryCode
              }
            }, {
              plugin : 'hidden',
              options : {
                name : 'dictCode',
                value : selectedDatas.dictCode
              }
            }
        ]
      });
    },
    icon : 'set',
    text : 'set',
    dialog : {
      size : {
        cols : 3,
        rows : 12
      }
    },
    form : {
      plugins : compDictMgmtFormPlugins
    }
  },
  searchForm : [
      {
        plugin : 'textbox',
        options : {
          name : 'categoryName',
          cls : 'fuzzy-left fuzzy-right'
        }
      }, {
        plugin : 'textbox',
        options : {
          key : 'categoryCode',
          name : 'categoryCode',
          cls : 'fuzzy-left fuzzy-right'
        }
      }, {
        plugin : 'textbox',
        options : {
          name : 'dictName',
          cls : 'fuzzy-left fuzzy-right'
        }
      }, {
        plugin : 'textbox',
        options : {
          name : 'dictCode',
          cls : 'fuzzy-left fuzzy-right'
        }
      }
  ]
}));
