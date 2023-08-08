import axios from 'axios';

const http = axios.create({
  baseURL: "http://localhost:9000"
});

export default {
    
    getPlayerPoints(id) {
        return http.get(`/points/${id}`);
    },

    getPlayerAssists(id) {
        return http.get(`/assists/${id}`);
    },

    getPlayerBlocks(id) {
        return http.get(`/blocks/${id}`);
    },

    getPlayerRebounds(id) {
        return http.get(`/rebounds/${id}`);
    },

    getPlayerSteals(id) {
        return http.get(`/steals/${id}`);
    },

    getPlayerThrees(id) {
        return http.get(`/threes/${id}`);
    },

    getPlayerFieldGoals(id) {
        return http.get(`/field-goals/${id}`);
    },

    getPlayerFreeThrows(id) {
        return http.get(`/free-throws/${id}`)
    }
}