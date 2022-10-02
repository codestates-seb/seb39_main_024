export default function Pagination({ posts, currentPage, setCurrentPage }) {
  return (
    <div className="bg-pink text-center">
      <button
        disabled={currentPage < 1}
        onClick={() => setCurrentPage((prev) => prev - 1)}
      >
        &#8249;
      </button>
      {currentPage + 1}
      <button
        disabled={posts.length === 0}
        onClick={() => setCurrentPage((prev) => prev + 1)}
      >
        &#8250;
      </button>
    </div>
  );
}
