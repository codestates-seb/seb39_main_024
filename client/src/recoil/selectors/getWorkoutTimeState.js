import { selector } from 'recoil';
import { memberIdState } from '../atoms/memberIdState';
import { authorizationState } from '../atoms/authorizationState';
import instance from '../../service/request';

export const getWorkoutTimeState = selector({
  key: 'getWorkoutTimeState',
  get: async ({ get }) => {
    try {
      const memberId = get(memberIdState);
      const token = get(authorizationState);
      const data = await (
        await instance.get(`/members/${memberId}`, {
          headers: {
            'Content-Type': 'application/json',
            Authorization: token,
          },
        })
      ).data;
      return data;
    } catch (err) {
      console.log('err', err);
    }
  },
});
