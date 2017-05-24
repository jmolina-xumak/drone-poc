
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
                    if(item.hasChildren){
                        let isExpanded = !item.isExpanded;

                        this.menu.pages = this.menu.pages.map(function(el){
                            el.isExpanded = false;
                            return el;
                        });
                        if(isExpanded === true ){
                            item.isExpanded = isExpanded;
                        }
                    }else {
                        return false;
                    }
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
                },
                mouseIn: function() {
                    $('.MainNavigation').addClass('isSlidedIn').removeClass('isSlidedOut');
                },
                mouseOut: function(){
                    $('.MainNavigation').addClass('isSlidedOut').removeClass('isSlidedIn');    
                },
                mouseEnter: function(item){
                    
                    if(item.hasChildren) {
                        $('.MainNavigation-subList.isExpanded').css({
                            'animation':'in .5s .3s ease forwards'
                        });
                    } else {
                        return false;
                    }

                },
                mouseLeave: function(item){

                    if(item.hasChildren) {
                        $('.MainNavigation-subList.isExpanded').css({
                            'animation':'out .5s ease forwards'
                        });
                    } else {
                        return false;
                    }

                }
            }
        })
    });
});