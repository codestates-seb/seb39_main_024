import { selector } from 'recoil';
import axios from 'axios';

import { postBoardState } from './postBoardState';

export const postReadState = selector({
  key: 'postReadState',
  get: async ({ get }) => {
    const id = get(postBoardState);
    const res = await axios.get(`http://211.41.205.19:8080/board/${id}`);
    const data = await res.data.data;
    return data;
  },
});
