import axios from 'axios';

const instance = axios.create({
  baseURL: `https://server.main024.shop/`,
});

export default instance;
