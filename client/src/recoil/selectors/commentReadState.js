import { selector } from 'recoil';
import instance from '../../service/request';

import { boardIdState } from '../atoms/boardIdState';

export const commentReadState = selector({
  key: 'commentReadState',
  get: async ({ get }) => {
    const id = get(boardIdState);
    const res = await instance.get(`/board/${id}/comment`);
    const data = await res.data.data;
    return data;
  },
});
