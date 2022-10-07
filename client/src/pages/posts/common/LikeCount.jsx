import { useRecoilValue } from 'recoil';
import instance from '../../../service/request';
import { boardIdState } from '../../../recoil/atoms/boardIdState';
import { memberIdState } from '../../../recoil/atoms/memberIdState';
import { isCheckLikeState } from '../../../recoil/selectors/isCheckLikeState';
import { isLoginState } from '../../../recoil/atoms/isLoginState';

export default function LikeCount({ like }) {
  const boardId = useRecoilValue(boardIdState);
  const memberId = useRecoilValue(memberIdState);
  const isCheckLike = useRecoilValue(isCheckLikeState(boardId));
  const isLogin = useRecoilValue(isLoginState);

  const likeHandler = async () => {
    try {
      await instance.post(`/board/${boardId}/like?memberId=${memberId}`);
      if (!isCheckLike) {
        alert('해당 글에 좋아요를 눌렀습니다.');
      }
      if (isCheckLike) {
        alert('해당 글에 좋아요를 취소했습니다.');
      }
      window.location.replace('/posts/read');
    } catch (err) {
      console.log('err', err);
    }
  };

  return (
    <section>
      {isLogin && (
        <>
          <button className="mr-2" onClick={likeHandler}>
            {isCheckLike && <span className="text-xl">&#10084;</span>}
            {!isCheckLike && <span className="text-xl">&#129293;</span>}
          </button>
          <span className="text-xl">{like}</span>
        </>
      )}
      {!isLogin && (
        <>
          <button
            className="mr-2"
            onClick={() => alert('로그인 후 이용해주세요.')}
          >
            <span className="text-xl">&#129293;</span>
          </button>
          <span className="text-xl">{like}</span>
        </>
      )}
    </section>
  );
}
