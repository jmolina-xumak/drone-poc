'use strict';

// TODO: code logic here

Vue.component('hero', {
    template: '#hero',
    props: ['bg-image', 'bg-color', 'title', 'link-url'],
    computed: {
        background: function background() {
            return this.bgColor + ' url(' + this.bgImage + ')';
        }
    },
    mounted: function mounted() {
        TweenMax.fromTo('.Hero', 1, {
            css: {
                opacity: 0
            }
        }, {
            css: {
                opacity: 1
            }
        });
    }
});

Vue.component('xumak-text', {
    template: '<p class="XumakText"><slot></slot></p>',
    mounted: function mounted() {
        TweenMax.fromTo('.XumakText', 2, {
            css: {
                left: '100vw'
            }
        }, {
            css: {
                left: '-5vw'
            }
        });
    }

});