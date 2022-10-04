import axios from 'axios';
// import https from 'https';

const instance = axios.create({
  baseURL: `https://flyaway.main024.shop/`,
});

export default instance;
