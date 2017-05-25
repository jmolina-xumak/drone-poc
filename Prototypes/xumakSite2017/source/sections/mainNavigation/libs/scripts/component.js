
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
                    if(item.hasChildren && $(window).width() < 1224){
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
                    if($(window).width() > 1224) {
                        $('.MainNavigation').addClass('isSlidedIn').removeClass('isSlidedOut');
                        if($('.MainNavigation-overlay').length > 0){
                                return false;
                        } else {
                            $('<div class="MainNavigation-overlay"/>').appendTo('body').animate({
                                opacity: .8
                            }, 500);
                        }
                    }
                },
                mouseOut: function(){
                    if($(window).width() > 1224) { 
                        $('.MainNavigation').addClass('isSlidedOut').removeClass('isSlidedIn');    
                        
                        $('.MainNavigation-overlay').animate({
                            opacity: 0
                        }, 500, function() {
                            $(this).remove();
                        });
                    }
                },
                mouseEnter: function(item, el){
                    if(item.hasChildren && $(window).width() > 1224) {
                        var listItem = $(el.target).parents('.MainNavigation-item');
                        $(el.target).parents('.MainNavigation-item').find('.MainNavigation-link').addClass('isActive');
                        $(listItem[0]).find('.MainNavigation-subList').addClass('isExpanded').css({
                            'animation':'in .5s .3s ease forwards'
                        });;
                    } else {
                        return false;
                    }
                },
                mouseLeave: function(item, el){
                    console.log('el', $(el.target).find('.MainNavigation-link').removeClass('isActive'));
                    if(item.hasChildren && $(window).width() > 1224) {  
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