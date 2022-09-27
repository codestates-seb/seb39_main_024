import { selector } from 'recoil';
import axios from 'axios';

import { boardIdState } from '../atoms/boardIdState';

export const commentReadState = selector({
  key: 'commentReadState',
  get: async ({ get }) => {
    const id = get(boardIdState);
    const res = await axios.get(
      `http://211.41.205.19:8080/board/${id}/comment`
    );
    const data = await res.data.data;
    return data;
  },
});
