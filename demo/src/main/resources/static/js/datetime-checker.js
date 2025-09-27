/**
 * Date Time Checker Application JavaScript
 * Handles form validation, AJAX requests, and modal interactions
 */

// Clear form function
function clearForm() {
    document.getElementById('day').value = '';
    document.getElementById('month').value = '';
    document.getElementById('year').value = '';
    document.getElementById('messageArea').style.display = 'none';
}

// Validate input function according to URD specifications
function validateInput(value, fieldName, min, max) {
    if (value === '') {
        showModal(`Input data for ${fieldName} cannot be empty.`);
        return false;
    }
    
    if (isNaN(value)) {
        showModal(`Input data for ${fieldName} is incorrect format!`);
        return false;
    }
    
    const num = parseInt(value);
    if (num < min || num > max) {
        showModal(`Input data for ${fieldName} is out of range!`);
        return false;
    }
    
    return true;
}

// Check DateTime function - main validation and processing
async function checkDateTime() {
    const day = document.getElementById('day').value.trim();
    const month = document.getElementById('month').value.trim();
    const year = document.getElementById('year').value.trim();
    
    // Validate inputs according to URD specifications
    if (!validateInput(day, 'Day', 1, 31)) return;
    if (!validateInput(month, 'Month', 1, 12)) return;
    if (!validateInput(year, 'Year', 1000, 3000)) return;
    
    // Create model object
    const dateModel = {
        day: day,
        month: month,
        year: year
    };
    
    try {
        // Send request to backend
        const response = await fetch('/check', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(dateModel)
        });
        
        if (response.ok) {
            const result = await response.json();
            showModal(result.message);
        } else {
            showModal('Error occurred while checking the date.');
        }
    } catch (error) {
        showModal('Network error occurred. Please try again.');
    }
}

// Modal Management Functions

// Show modal message
function showModal(message) {
    document.getElementById('modalMessage').textContent = message;
    document.getElementById('messageModal').style.display = 'block';
}

// Close modal
function closeModal() {
    document.getElementById('messageModal').style.display = 'none';
}

// Show confirmation modal for exit
function showConfirmModal() {
    document.getElementById('confirmModal').style.display = 'block';
}

// Close confirmation modal
function closeConfirmModal() {
    document.getElementById('confirmModal').style.display = 'none';
}

// Confirm exit - try multiple methods to close the tab/window
function confirmExit() {
    // Method 1: Try to close the window (works if opened by script)
    window.close();
    
    // Method 2: If window.close() doesn't work, redirect to about:blank
    setTimeout(function() {
        window.location.href = 'about:blank';
    }, 100);
    
    // Method 3: Alternative - redirect to a blank page or show exit message
    setTimeout(function() {
        document.body.innerHTML = '<div style="display: flex; justify-content: center; align-items: center; height: 100vh; font-family: Arial; font-size: 24px; color: #666;">Application Closed<br><small style="font-size: 14px; margin-top: 10px;">You can now close this tab</small></div>';
    }, 200);
}

// Direct exit function for immediate tab closure
function exitApplication() {
    window.close();
}

// Event Listeners Setup
document.addEventListener('DOMContentLoaded', function() {
    // Handle Enter key press for form submission
    document.addEventListener('keypress', function(event) {
        if (event.key === 'Enter') {
            checkDateTime();
        }
    });
    
    // Handle window close event (simulating X button from URD)
    window.addEventListener('beforeunload', function(event) {
        event.preventDefault();
        showConfirmModal();
        return '';
    });
    
    // Close modal when clicking outside
    window.addEventListener('click', function(event) {
        const messageModal = document.getElementById('messageModal');
        const confirmModal = document.getElementById('confirmModal');
        
        if (event.target === messageModal) {
            closeModal();
        }
        if (event.target === confirmModal) {
            closeConfirmModal();
        }
    });
    
    // Add focus styling for better UX
    const inputs = document.querySelectorAll('input[type="text"]');
    inputs.forEach(input => {
        input.addEventListener('focus', function() {
            this.style.borderColor = '#007bff';
        });
        
        input.addEventListener('blur', function() {
            this.style.borderColor = '#ccc';
        });
    });
});
