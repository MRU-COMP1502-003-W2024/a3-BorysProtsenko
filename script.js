document.addEventListener('DOMContentLoaded', function() {
    const grid = document.getElementById('image-grid');
    if (!grid) {
        console.error('Grid container not found!');
        return;
    }

    const gridSize = 3; // Defines the grid size (3x3)
    const pieceSize = 200; // Size of each piece

    grid.style.width = `${pieceSize * gridSize}px`;
    grid.style.height = `${pieceSize * gridSize}px`;
    grid.style.position = 'relative';

    // Generate an array to hold the indices of grid positions
    let indices = Array.from({ length: gridSize * gridSize }, (_, i) => i);
    
    // Shuffle the indices to randomize piece positions
    for (let i = indices.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        [indices[i], indices[j]] = [indices[j], indices[i]];
    }

    // Dynamically creating each piece of the image with randomized positions
    indices.forEach((pos, index) => {
        const row = Math.floor(pos / gridSize);
        const col = pos % gridSize;

        const piece = document.createElement('div');
        piece.classList.add('image-piece', 'draggable');
        piece.setAttribute('draggable', 'true');
        piece.style.width = `${pieceSize}px`;
        piece.style.height = `${pieceSize}px`;
        piece.style.backgroundImage = "url('images/kiev.jpg')";
        piece.style.backgroundSize = `${pieceSize * gridSize}px ${pieceSize * gridSize}px`;
        piece.style.backgroundPosition = `-${col * pieceSize}px -${row * pieceSize}px`;
        piece.style.position = 'absolute';

        // Determine the initial top and left positions based on the original index
        const initialRow = Math.floor(index / gridSize);
        const initialCol = index % gridSize;
        piece.style.left = `${initialCol * pieceSize}px`;
        piece.style.top = `${initialRow * pieceSize}px`;

        grid.appendChild(piece);

        makeDraggable(piece);
    });

    function makeDraggable(draggable) {
        let active = false;
        let currentX;
        let currentY;
        let initialX;
        let initialY;
        let xOffset = 0;
        let yOffset = 0;

        draggable.addEventListener('mousedown', function(e) {
            initialX = e.clientX - xOffset;
            initialY = e.clientY - yOffset;

            if (e.target === draggable) {
                active = true;
                draggable.style.cursor = 'grabbing';
            }
        });

        document.addEventListener('mouseup', function() {
            initialX = currentX;
            initialY = currentY;
            active = false;
            draggable.style.cursor = 'grab';
        });

        document.addEventListener('mousemove', function(e) {
            if (active) {
                e.preventDefault();
              
                currentX = e.clientX - initialX;
                currentY = e.clientY - initialY;

                xOffset = currentX;
                yOffset = currentY;

                setTranslate(currentX, currentY, draggable);
            }
        });

        function setTranslate(xPos, yPos, el) {
            el.style.transform = "translate3d(" + xPos + "px, " + yPos + "px, 0)";
        }
        
    }
});
