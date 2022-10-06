import { selector } from 'recoil';
import instance from '../../service/request';

import { boardIdState } from '../atoms/boardIdState';
import { authorizationState } from '../atoms/authorizationState';

export const commentReadState = selector({
  key: 'commentReadState',
  get: async ({ get }) => {
    try {
      const id = get(boardIdState);
      const token = get(authorizationState);
      const res = await instance.get(`/board/${id}/comment`, {
        headers: {
          'Content-Type': 'application/json',
          Authorization: token,
        },
      });
      const data = await res.data.data;
      return data;
    } catch (err) {
      console.log('err', err);
    }
  },
});
