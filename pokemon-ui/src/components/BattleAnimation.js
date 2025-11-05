export function BattleAnimation() {
  return `
    <div class="battle-animation">
      <div class="battle-flash"></div>
      <div class="battle-sparks">
        <div class="spark"></div>
        <div class="spark"></div>
        <div class="spark"></div>
      </div>
    </div>
  `;
}

// Fonction pour démarrer l'animation et jouer le son
export function startBattleAnimation() {
  const audio = new Audio('/sounds/battle-sound.mp3');
  const animation = document.querySelector('.battle-animation');
  
  animation.classList.add('active');
  audio.play().catch(err => console.warn('Audio could not be played:', err));
  
  // Retirer l'animation après 2 secondes
  setTimeout(() => {
    animation.classList.remove('active');
  }, 2000);
}