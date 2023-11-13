<template>
  <div>
    <BuyersNav />
    <router-view></router-view>
  </div>
</template>

<script>
import BuyersNav from "@/components/Buyers/BuyersNav.vue";

export default {
  components: {
    BuyersNav,
  },
  data() {
    return {
      
    };
  },
  methods: {
 
  },
};
</script>
