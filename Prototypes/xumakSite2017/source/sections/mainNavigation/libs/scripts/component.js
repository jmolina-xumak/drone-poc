
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
                var vm = this;
                this.menu = this.structure;
                this.menu.pages = this.menu.pages.map(function(el) {
                    vm.$set(el,'isExpanded', false);
                    return el;
                });
                vm.$set(this.menu, 'isVisible', false);
            },
            methods: {
                toggleSubMenu: function(item){
                    let isExpanded = !item.isExpanded;

                    this.menu.pages = this.menu.pages.map(function(el){
                        el.isExpanded = false;
                        return el;
                    });
                    if(isExpanded === true ){
                        item.isExpanded = isExpanded;
                    }

/*                    if(hasChilds){
                        //toggleMenu
                        alert('Toggle menu');
                    } else {
                        return false;
                    }*/

                },
                toggleMenu: function() {
                    this.menu.isVisible = !this.menu.isVisible;

                    

                    if ( this.menu.isVisible ) {
                        $('<div class="MainNavigation-overlay"/>').appendTo('body').animate({
                            opacity: .8
                        }, 500);
                      
                    } else {
                        $('.MainNavigation-overlay').animate({
                            opacity: 0
                        }, 500, function() {
                            $(this).remove();
                        });
                    }
                }

            }
        })
    });
});