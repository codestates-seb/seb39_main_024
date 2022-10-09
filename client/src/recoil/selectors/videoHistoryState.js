import { selector } from 'recoil';
import instance from '../../service/request';
import { memberIdState } from '../atoms/memberIdState';
import { authorizationState } from '../atoms/authorizationState';

export const videoHistoryState = selector({
  key: 'videoHistoryState',
  get: async ({ get }) => {
    try {
      const memberId = get(memberIdState);
      const token = get(authorizationState);
      const res = await instance.get(`/members/${memberId}/video`, {
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
