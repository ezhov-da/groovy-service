Ext.Ajax.timeout = 60000;

Ext.define('Files', {
    extend: 'Ext.data.Model',
    fields: [
        {name: 'name', type: 'string'},
        {name: 'size', type: 'int'},
        {name: 'pathToDownload', type: 'string'},
    ]
});

var store = Ext.create('Ext.data.Store', {
    extend: 'Ext.data.Store',
    model: 'Files',
    proxy: {
        type: 'ajax',
        url: 'insListFiles.groovy'
    },
    autoLoad: true
});

var panelFileList = Ext.create('Ext.grid.Panel', {
    title: 'Список файлов',
    region: 'center',
    layout: 'fit',
    store: store,
    columns: {
        items: [
            {
                text: 'Файл', dataIndex: 'name'
            },
            {
                text: 'Размер', dataIndex: 'size'
            },
            {
                text: 'Загрузить',
                dataIndex: 'pathToDownload',
                renderer: function (value) {
                    return "<a href='" + value + "'>Загрузить</a>";
                }

            }
        ],
        defaults: {
            flex: 1
        }

    },
    listeners: {
        select: function (record, index, eOpts) {
            console.log(record);
            console.log(eOpts);
            var name = record.selected.items[0].data.name;
            Ext.Ajax.request({
                url: 'insFileInfo.groovy?name=' + name,
                method: 'GET',
                params: {
                    id: 1
                },
                success: function (response) {
                    var text = response.responseText;
                    Ext.getCmp('nameFile').setValue(name);
                    Ext.getCmp('bodyFile').setValue(Ext.decode(text).msg);
                    console.log(text);
                },
            });
        }
    }
});

var formCreateRewriteFile = Ext.create('Ext.form.Panel', {
    title: 'Создать/отредактировать файл',
    region: 'east',
    width: '70%',
    layout: 'border',
    split: true,
    url: 'insFileSaver.groovy',
    items: [
        {
            region: 'north',
            fieldLabel: 'Название файла:',
            xtype: 'textfield',
            id: 'nameFile',
            name: 'name',
            allowBlank: false
        }, {
            region: 'center',
            fieldLabel: 'Текст файла:',
            xtype: 'textarea',
            id: 'bodyFile',
            name: 'text',
            allowBlank: false
        }, {
            region: 'south',
            xtype: 'label',
        }

    ],
    buttons: [
        {
            text: 'Выполнить',
            formBind: true, //only enabled once the form is valid
            disabled: true,
            handler: function () {
                var form = formCreateRewriteFile.getForm();
                if (form.isValid()) {
                    form.submit({
                        success: function (form, action) {
                            formCreateRewriteFile.showInfo(form, action);
                        },
                        failure: function (form, action) {
                            formCreateRewriteFile.showInfo(form, action);
                        }
                    });
                }
            }
        }]
    ,
    showInfo: function (form, action) {
        console.log(action);
        formCreateRewriteFile.items.items[2].setText(action.response.responseText);
        store.reload();
    }
});

var basicPanel = Ext.create('Ext.panel.Panel', {
    title: 'Админка',
    layout: 'border',
    items: [
        panelFileList,
        formCreateRewriteFile
    ],
    tbar: [
        {xtype: 'tbfill'},
        {
            xtype: 'label',
            html: '<a href="insLogout.groovy">Logout</a>'
        }
    ]
});

Ext.application({
    name: 'Groovy service',
    launch: function () {
        Ext.create('Ext.container.Viewport', {
            layout: 'fit',
            items: [basicPanel]
        });
    }
});