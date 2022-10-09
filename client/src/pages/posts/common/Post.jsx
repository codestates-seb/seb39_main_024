import { useNavigate } from 'react-router-dom';
import { useSetRecoilState, useRecoilValue } from 'recoil';

import { boardIdState } from '../../../recoil/atoms/boardIdState';
import { imgReadState } from '../../../recoil/selectors/imgReadState';
import { isCheckLikeState } from '../../../recoil/selectors/isCheckLikeState';

export default function Post({ items }) {
  const navigation = useNavigate();

  const setBoardId = useSetRecoilState(boardIdState);
  const imgUrl = useRecoilValue(imgReadState(items.imageId[0]));
  const isCheckLike = useRecoilValue(isCheckLikeState(items.boardId));

  const postReadHandler = () => {
    setBoardId(items.boardId);
    navigation('/posts/read');
  };

  return (
    <section
      className="flex flex-col justify-between p-2.5 m-1.5 border-solid border border-slate-500 rounded-sm hover:cursor-pointer hover:bg-slate-200/30"
      onClick={postReadHandler}
      aria-hidden="true"
    >
      <img
        className="border-solid border border-zinc-300 rounded-sm w-full h-[446px] md:h-350"
        src={imgUrl}
        alt="img"
      />
      <p className="text-start text-2xl py-2">{items.title}</p>
      <div className="flex justify-between">
        <span className="text-xl">&#128100; {items.memberName}</span>
        <div className="flex row-end items-center text-lg">
          {isCheckLike && (
            <span className="sm:text-2xl text-3xl text-rose-500">&#9829;</span>
          )}
          {!isCheckLike && (
            <span className="sm:text-xl text-2xl">&#129293;</span>
          )}
          <span className="ml-2">{items.likeCount}</span>
        </div>
      </div>
    </section>
  );
}
