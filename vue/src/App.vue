<template>
  <div id="app">
    <div id="nav">
    <router-link id="home-link" v-bind:to="{ name: 'home' }">HOME</router-link>
      <router-link id="about-link" v-bind:to="{ name: 'about' }">ABOUT</router-link>
    </div>
    <router-view />
  </div>
</template>
<style scoped>
#nav {
  background-color: black;
  color: orangered;
  text-align: center;
  font-family: Calibri, Geneva, Tahoma, sans-serif;
}

#home-link, #about-link {
  color: orange;
  margin: 10px;
  display: inline-block;
  padding: 10px 20px;
  background-color: orange;
  color: black;
  text-decoration: none;
  border-radius: 25px;
  transition: background-color 0.3s;
  width: 60px;
  letter-spacing: 2px;
}

#home-link:hover, #about-link:hover {
  background-color: orangered;
}

</style>
