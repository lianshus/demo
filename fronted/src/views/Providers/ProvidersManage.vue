<template>
    <div>
      <ProvidersNav />
      <router-view></router-view>
    </div>
  </template>
  
  <script>
  import ProvidersNav from "@/components/Providers/ProvidersNav.vue";
  
  export default {
    components: {
      ProvidersNav,
    },
    data() {
      return {
        
      };
    },
    methods: {
   
    },
  };
  </script>
  