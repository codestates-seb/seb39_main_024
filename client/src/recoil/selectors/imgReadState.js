import { selector } from 'recoil';
import axios from 'axios';

export const imgReadState = selector({
  key: 'imgReadState',
  get: async () => {
    try {
      const res = await axios.get('https://211.41.205.19:8080/members/1/image');
      const data = await res.data;
      return data;
    } catch (err) {
      console.log('err', err);
    }
  },
});
