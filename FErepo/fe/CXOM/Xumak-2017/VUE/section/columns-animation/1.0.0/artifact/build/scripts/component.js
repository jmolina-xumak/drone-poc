'use strict';

// TODO: code logic here

Vue.component('xmk-columns', {
    props: ['time', 'controller'],
    data: function data() {
        return {};
    },
    methods: {
        focusIn: function focusIn() {
            this.focused = true;
        },
        focusOut: function focusOut() {
            this.focused = false;
        }
    },
    mounted: function mounted() {

        var tl = new TimelineLite(),
            oneColumn = $(".one-box"),
            twoColumn = $(".two-box"),
            threeColumn = $(".three-box"),
            ColumnsAnimation = $(".ColumnsAnimation-title");

        tl.from(ColumnsAnimation, 1, { top: 250 }, "0");
        tl.from(oneColumn, 1, { top: 250 }, "-=0.4");
        tl.from(twoColumn, 1, { top: 250 }, "-=0.7");
        tl.from(threeColumn, 1, { top: 250 }, "-=0.7");
        tl.play();

        new ScrollMagic.Scene({
            duration: '20%'
        }).setTween(tl).triggerHook(0.55).triggerElement('#ColumnsAnimation').addIndicators({ name: " ColumnsAnimation " }).addTo(this.controller);
    },
    template: '<div><slot></slot></div>'
});