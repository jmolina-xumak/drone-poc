// TODO: code logic here
(function () {

    Vue.component( 'menu-accesibility', {
        template:"#menu",
        data: function(){
            return { 
                menu: {
                    items: [
                        {
                            text: "item 1",
                            ariaExpandend: false,
                            submenu: {
                                items :[
                                    { text: "item 1"},
                                    { text: "item 2"},
                                    { text: "item 3"},
                                ],
                                ariaHidden: false
                            }
                        },
                        {   
                            text: "item 2",
                            ariaExpandend: false,
                            submenu: {
                                items :[
                                    { text: "item 1", },
                                    { text: "item 2"},
                                    { text: "item 3"},
                                ],
                                ariaHidden: false
                            }
                        }
                    ]
                }
            }
        },
        methods: {
            enterItem: function(item) {
            item.ariaExpandend = ! item.ariaExpandend;
            item.submenu.ariaHidden = ! item.submenu.ariaHidden;
                
            },

            subItemEvent: function() {
                console.log(event);   
            },
        }
    });

    new Vue({
        el: "#app1",
        data: {},
    });

})();

