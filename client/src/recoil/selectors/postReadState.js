import { selector } from 'recoil';
import axios from 'axios';

import { boardIdState } from '../atoms/boardIdState';

export const postReadState = selector({
  key: 'postReadState',
  get: async ({ get }) => {
    const id = get(boardIdState);
    const res = await axios.get(`https://211.41.205.19:8080/board/${id}`);
    const data = await res.data.data;
    return data;
  },
});
