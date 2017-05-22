
// TODO: code logic here
Vue.component('mainNavigation', function mainNavCreator(resolve, reject) {
    $.ajax('/assets/templates/main-navigation.html').done(function(template){
        resolve({
            props:['structure', 'dataImagePath', 'dataImageAltText', 'dataRootPath'],
            template: template,
            data: function(){
                return {
                    menu: {}
                }
            },
            created: function(){
                this.menu = this.structure;
            },
            methods: {
                toggleSubMenu: function(hasChilds){
                    if(hasChilds){
                        //toggleMenu
                        alert('Toggle menu');
                    } else {
                        return false;
                    }

                }
            }
        })
    });
});