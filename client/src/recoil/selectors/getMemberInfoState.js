import { selector } from 'recoil';
import instance from '../../service/request';
import { memberIdState } from '../atoms/memberIdState';
import { authorizationState } from '../atoms/authorizationState';

export const getMemberInfoState = selector({
  key: 'getMemberInfoState',
  get: async ({ get }) => {
    try {
      const memberId = get(memberIdState);
      const token = get(authorizationState);
      const res = await instance.get(`/members/${memberId}`, {
        headers: {
          'Content-Type': 'application/json',
          Authorization: token,
        },
      });
      const data = await res.data;
      return data;
    } catch (err) {
      console.log('err', err);
    }
  },
});
