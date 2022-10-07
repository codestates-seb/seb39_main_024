import { useRecoilValue } from 'recoil';
import instance from '../../../service/request';
import { boardIdState } from '../../../recoil/atoms/boardIdState';
import { memberIdState } from '../../../recoil/atoms/memberIdState';
import { isCheckLikeState } from '../../../recoil/selectors/isCheckLikeState';

export default function LikeCount({ like }) {
  const boardId = useRecoilValue(boardIdState);
  const memberId = useRecoilValue(memberIdState);
  const isCheckLike = useRecoilValue(isCheckLikeState(boardId));

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
      <button className="mr-2" onClick={likeHandler}>
        {isCheckLike && <span className="text-xl">&#10084;</span>}
        {!isCheckLike && <span className="text-xl">&#129293;</span>}
      </button>
      <span className="text-xl">{like}</span>
    </section>
  );
}
