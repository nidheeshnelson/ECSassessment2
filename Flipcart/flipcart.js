const slider = document.querySelector('.slider');
const prevBtn = document.querySelector('.prev-btn');
const nextBtn = document.querySelector('.next-btn');
let direction;

prevBtn.addEventListener('click', function () {
  direction = -1;
  carousel();
});

nextBtn.addEventListener('click', function () {
  direction = 1;
  carousel();
});

function carousel() {
  const sliderWidth = slider.offsetWidth;
  const cardWidth = document.querySelector('.card').offsetWidth;
  const cardsMargin = 20;
  const cardsPerPage = Math.floor(sliderWidth / (cardWidth + cardsMargin));
  const scrollDistance = (cardWidth + cardsMargin) * cardsPerPage * direction;

  slider.scrollLeft += scrollDistance;
}