/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ['./src/**/*.{html,js,jsx,ts,tsx}'],
  theme: {
    extend: {
      colors: {
        pink: '#FFDADA',
      },
      width: {
        768: '768px',
      },
    },
    maxWidth: {
      480: '480px',
    },
    screens: {
      sm: { max: '767px' },
      // => @media (max: '767px') { ... }
      md: { min: '768px' },
    },
  },
  plugins: [],
};
