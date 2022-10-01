import { useState } from 'react';
import { useRecoilValue } from 'recoil';

import { postsCategoryState } from '../../../recoil/selectors/postsCategoryState';
import Post from '../common/Post';
import Pagination from '../../../components/Pagination';

export default function Meal() {
  const [currentPage, setCurrentPage] = useState(0);
  const posts = useRecoilValue(postsCategoryState(currentPage));

  return (
    <>
      <div className="flex flex-wrap justify-center items-start bg-pink">
        {posts.map((post) => (
          <Post key={post.boardId} items={post} />
        ))}
      </div>
      <Pagination
        posts={posts}
        currentPage={currentPage}
        setCurrentPage={setCurrentPage}
      />
    </>
  );
}
