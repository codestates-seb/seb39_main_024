import { selector } from 'recoil';
import instance from '../../service/request';
import { memberIdState } from '../atoms/memberIdState';
import { authorizationState } from '../atoms/authorizationState';

export const memberImgState = selector({
  key: 'memberImgState',
  get: async ({ get }) => {
    try {
      const memberId = get(memberIdState);
      const token = get(authorizationState);
      const res = await instance.get(`/members/${memberId}/image`, {
        headers: {
          'Content-Type': 'application/json',
          Authorization: token,
        },
      });
      const data = await res.data;
      return data;
    } catch (err) {
      const errMsg = err.response.data.message.includes(
        'The Token has expired'
      );
      if (errMsg) {
        return 'token';
      }
      console.log('err', err);
    }
  },
});
