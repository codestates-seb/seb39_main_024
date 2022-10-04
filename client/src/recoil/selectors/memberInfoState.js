import { selector } from 'recoil';
import instance from '../../service/request';
import { memberIdState } from '../atoms/memberIdState';

export const memberInfoState = selector({
  key: 'memberInfoState',
  get: async ({ get }) => {
    const memberId = get(memberIdState);
    const res = await instance.get(`/members/${memberId}`);
    const data = await res.data;
    return data;
  },
});
