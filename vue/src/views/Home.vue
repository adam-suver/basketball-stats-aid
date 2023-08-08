<template>
  <div class="home">
    <h1>Basketball Betting Aid</h1>
    <p>You must be authenticated to see this</p>
  <form @submit.prevent="retrieveStats">
  <select name="Player" id="playerDropdown" v-model="selectedPlayerId">
    <option v-for="player in playerList" :key="player.player_id" 
    :value="player.player_id" required
    >{{player.last_name}}, {{player.first_name}} - {{player.player_id}}</option>
  </select>
  
  <select name="Category" id="category" v-model="statRequest.category" required>
    <option value="" disabled selected>Select A Stat Category</option>
    <option value="assists" :selected="statRequest.category === 'assists'">Assists</option>
    <option value="blocks" :selected="statRequest.category === 'blocks'">Blocks</option>
    <option value="fieldGoals" :selected="statRequest.category === 'fieldGoals'">Field Goals Made</option>
    <option value="freeThrows" :selected="statRequest.category === 'freeThrows'">Free Throws Made</option>
    <option value="points" :selected="statRequest.category === 'points'">Points</option>
    <option value="rebounds" :selected="statRequest.category === 'rebounds'">Rebounds</option>
    <option value="steals" :selected="statRequest.category === 'steals'">Steals</option>
    <option value="threePointers" :selected="statRequest.category === 'threePointers'">Three Pointers Made</option>
  </select>
  
  <select name="Line" id="line" v-model="selectedLine">
    <option value="" disabled selected>Select A Line</option>
  </select>
  
  <button type="submit">Submit</button>
  </form>
  <table v-show="requestedStats != null && (statRequest.category === 'assists' || 
  statRequest.category === 'blocks' || statRequest.category === 'points' || 
  statRequest.category === 'rebounds' || statRequest.category === 'steals')">
    <tr>
      <th>Date</th>
      <th>Value</th>
    </tr>
    <tr v-for="(value, date) in requestedStats" :key="date">
      <td>{{ date }}</td>
      <td>{{ value }}</td>
    </tr>
  </table>
  <table v-show="requestedStats != null && (statRequest.category === 'fieldGoals' 
  || statRequest.category === 'freeThrows' || statRequest.category === 'threePointers')">
    <tr>
      <th>Date</th>
      <th>Made</th>
      <th>Attempts</th>
    </tr>
    <tr v-for="(data, date) in requestedStats" :key="date">
      <td>{{date}}</td>
      <td>{{data.shotsMade}}</td>
      <td>{{data.attempts}}</td>
    </tr>
  </table>
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
      selectedPlayerId: Number(0),
      statRequest: {
        playerId: this.selectedPlayerId,
        category: "",
      },
      selectedLine: "",
      requestedStats: null,
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
        if (this.playerList.length > 0) {
            this.selectedPlayerId = this.playerList[0].player_id;
        }
      })
      .catch((error) => {
        console.log(error)
      })
    },
    retrieveStats() {
      this.requestedStats = null;
      if (this.statRequest.category === 'points') {
        StatService.getPlayerPoints(this.selectedPlayerId)
        .then((response) => {
          this.requestedStats = response.data;
        })
      } else if (this.statRequest.category === 'assists') {
        StatService.getPlayerAssists(this.selectedPlayerId)
        .then((response) => {
          this.requestedStats = response.data;
        })
      } else if (this.statRequest.category === 'blocks') {
        StatService.getPlayerBlocks(this.selectedPlayerId)
        .then((response) => {
          this.requestedStats = response.data;
        })
      } else if (this.statRequest.category === 'rebounds') {
        StatService.getPlayerRebounds(this.selectedPlayerId)
        .then((response) => {
          this.requestedStats = response.data;
        })
      } else if (this.statRequest.category === 'steals') {
        StatService.getPlayerSteals(this.selectedPlayerId)
        .then((response) => {
          this.requestedStats = response.data;
        })
      } else if (this.statRequest.category === 'fieldGoals') {
        StatService.getPlayerFieldGoals(this.selectedPlayerId)
        .then((response) => {
          this.requestedStats = response.data;
        })
      } else if (this.statRequest.category === 'freeThrows') {
        StatService.getPlayerFreeThrows(this.selectedPlayerId)
        .then((response) => {
          this.requestedStats = response.data;
        })
      } else if (this.statRequest.category === 'threePointers') {
        StatService.getPlayerThrees(this.selectedPlayerId)
        .then((response) => {
          this.requestedStats = response.data;
        })
      }

    }
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
