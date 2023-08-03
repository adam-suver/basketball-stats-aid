<template>
  <div class="home">
    <h1>Basketball Betting Aid</h1>
    <p>You must be authenticated to see this</p>

  <select name="Player" id="playerDropdown">
    <option value="" disabled selected>Select A Player</option>
    <option v-for="player in playerList" :key="player.player_id" :value="player">{{player.last_name}}, {{player.first_name}}</option>
  </select>
    <select name="Category" id="category">
    <option value="" disabled selected>Select A Stat Category</option>
    <option value="assists">Assists</option>
    <option value="blocks">Blocks</option>
    <option value="fieldGoals">Field Goals Made</option>
    <option value="freeThrows">Free Throws Made</option>
    <option value="points">Points</option>
    <option value="rebounds">Rebounds</option>
    <option value="steals">Steals</option>
    <option value="threePointers">Three Pointers Made</option>
  </select>
    <select name="Line" id="line">
    <option value="" disabled selected>Select A Line</option>
  </select>
  <button>Submit</button>
  </div>
</template>

<script>
import StatService from '../services/StatService.js';
import PlayerService from '../services/PlayerService.js';

export default {
  name: "home",
  data() {
    return {
      playerList: [],
    };
  },
  methods: {

    getPlayerPoints(id) {
      return StatService.getPlayerPoints(id);
    },

    getPlayerListFromDatabase() {
      PlayerService.getPlayersFromDatabase()
      .then((response) => {
        this.playerList = response.data.map((player) => {
          return {
            player_id: player.player_id,
            first_name: player.first_name,
            last_name: player.last_name,
          }
        })
      })
      .catch((error) => {
        console.log(error)
      })
    },
  },

  created() {
    this.getPlayerListFromDatabase();
  },
  
  mounted() {
    const lineDropdown = document.getElementById('line');
    function generateOptions(start, end, step) {
      for (let i = start; i <= end; i += step) {
        const option = document.createElement("option");
        option.value = i;
        option.textContent = i.toString();
        lineDropdown.appendChild(option);
      }
    }

    generateOptions(0.5, 50.5, 1);
  }
};
</script>
