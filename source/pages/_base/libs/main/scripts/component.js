// TODO: code logic here
(function(){
    Vue.component( 'xmk-input', {
        props: [
            'name',
            'label',
            'status',
            'description'
        ],
        data: function () {
            return {
                focused : false,
                input: ''
            }
        },
        methods:{
            focusIn: function(){
                this.focused = true;
            },
            focusOut:function(){
                this.focused = false;
            }
        },
        computed:{
          hasStatus : function (){
              return (this.input.length  > 0 || this.focused) ? this.status : '' ;
          }  
        },
        template:'#xmk-input'
    } );

    new Vue({}).$mount('#xmkApp');
})();