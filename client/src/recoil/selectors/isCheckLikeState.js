import { selectorFamily } from 'recoil';
import instance from '../../service/request';

import { memberIdState } from '../atoms/memberIdState';
import { authorizationState } from '../atoms/authorizationState';

export const isCheckLikeState = selectorFamily({
  key: 'isCheckLikeState',
  get:
    (boardId) =>
    async ({ get }) => {
      try {
        const memberId = get(memberIdState);
        const token = get(authorizationState);

        const res = await instance.get(
          `/board/${boardId}/checklike?memberId=${memberId}`,
          {
            headers: {
              'Content-Type': 'application/json',
              Authorization: token,
            },
          }
        );
        const data = await res.data;
        return data;
      } catch (err) {
        console.log('err', err);
      }
    },
});
